package engagetech.backend_challenge.api;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class ExpenseTest {

	@Test
	public void defaultConstructorTest() {
		Expense expense = new Expense();
		assertThat(expense.getId()).isNotNull();
		assertThat(expense.getDate()).isNull();;
		assertThat(expense.getReason()).isNull();
		assertThat(expense.getAmount()).isEqualTo(0.0);
		assertThat(expense.getVat()).isBetween(0.0, 0.00001);
	}

	@Test
	public void constructorWithoutIdTest() {
		Expense.vatTax = 0.2;
		Expense expense = new Expense("12/12/2001", "reason", 1.2);
		assertThat(expense.getId()).isNotNull();
		assertThat(expense.getReason()).isEqualTo("reason");
		assertThat(expense.getAmount()).isEqualTo(1.2);
		assertThat(expense.getVat()).isBetween(0.19, 0.20001);
	}
}
