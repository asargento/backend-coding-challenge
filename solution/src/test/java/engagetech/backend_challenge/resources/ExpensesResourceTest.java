package engagetech.backend_challenge.resources;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThat;

import engagetech.backend_challenge.backend_challengeApplication;
import engagetech.backend_challenge.backend_challengeConfiguration;
import engagetech.backend_challenge.api.Expense;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

public class ExpensesResourceTest {
	private static final String TMPDB = createTempDBName();
	
	private static String createTempDBName() {
		try {
			return File.createTempFile("test-db", null).getAbsolutePath();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
	}
	
	@AfterClass
	public static void cleanUp() {
		// Deleting the database
		
	}
	
	@ClassRule
	public static final DropwizardAppRule<backend_challengeConfiguration> Rule =
		new DropwizardAppRule<backend_challengeConfiguration>(backend_challengeApplication.class,
				ResourceHelpers.resourceFilePath("config.yml"),
				ConfigOverride.config("database.url", "jdbc:h2:" + TMPDB));
	
	@Test
	public void createExpense() {
		Expense expense = new Expense("22/12/2017", "reason", 12.45f);
		final Response response = Rule.client().target(getTargetUrl())
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(expense,MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.CREATED);
		assertThat(response.getHeaderString("location")).isNotNull();
	}
	
	@Test
	public void createExpenseWithTextBody() {
		final Response response = Rule.client().target(getTargetUrl())
				.request(MediaType.TEXT_PLAIN_TYPE)
				.post(Entity.entity("",MediaType.TEXT_PLAIN_TYPE));
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@Test
	public void createExpenseWithEmptyBody() {
		final Response response = Rule.client().target(getTargetUrl())
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(new Expense(),MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatus()).isEqualTo(422);
	}

	@Test
	public void createExpenseWithoutDate() {
		final Response response = Rule.client().target(getTargetUrl())
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(new Expense(null, "reason", 12f),MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatus()).isEqualTo(422);
	}

	@Test
	public void createExpenseWithoutReason() {
		final Response response = Rule.client().target(getTargetUrl())
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(new Expense("12/01/2001", "", 12f),MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatus()).isEqualTo(422);
	}

	@Test
	public void createExpenseWithInvalidDate() {
		final Response response = Rule.client().target(getTargetUrl())
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(new Expense("12-01-2001", "reason", 12f),MediaType.APPLICATION_JSON_TYPE));
		assertThat(response.getStatus()).isEqualTo(422);
	}
	
	public int createExpensesForList() {
		final int size = 3;
		for (int i=0; i<size; i++) {
			Rule.client().target(getTargetUrl())
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(new Expense("12/01/2018", "reason"+Integer.toString(i), 12.45f), MediaType.APPLICATION_JSON_TYPE));
		}
		return size;
	}
	
	@Test
	public void listExpenses() {
		int expensesCreatedNumber = createExpensesForList();
		final Response response = Rule.client().target(getTargetUrl())
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get();
		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		List<Expense> expenses = response.readEntity(new GenericType<List<Expense>>(){}); 
		assertThat(expenses).isNotNull();
		assertThat(expenses).hasSize(expensesCreatedNumber);
	}
	
	public void deleteExpensesForList() {
		// Clean after test. For now is not necessary
	}
	
	private String getTargetUrl() {
		return "http://localhost:" + Rule.getLocalPort() + "/expenses";
	}
}
