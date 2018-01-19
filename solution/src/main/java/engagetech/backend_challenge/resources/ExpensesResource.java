package engagetech.backend_challenge.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import engagetech.backend_challenge.api.Expense;
import engagetech.backend_challenge.db.ExpensesDAO;
import engagetech.backend_challenge.mappers.MapperException;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/expenses")
public class ExpensesResource {
	private final ExpensesDAO expensesDAO;
	
	public ExpensesResource(ExpensesDAO expensesDAO) {
		this.expensesDAO = expensesDAO;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@UnitOfWork
	public List<Expense> listExpenses() {
		return expensesDAO.listExpenses();
	}
	
	@POST
	@Consumes( {MediaType.APPLICATION_JSON} )
	@UnitOfWork
	public Response createExpense(@NotNull @Valid Expense expense) {
		try {
			expensesDAO.createExpense(expense);
		} catch (MapperException e) {
			return Response.status(422).build();
		}
		return Response.created(URI.create("/expenses/"+expense.getId())).build();
	}
}
