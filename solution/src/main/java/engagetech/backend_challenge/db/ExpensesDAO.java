package engagetech.backend_challenge.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import engagetech.backend_challenge.api.Expense;
import engagetech.backend_challenge.core.Expenses;
import engagetech.backend_challenge.mappers.DataMapperFactory;
import engagetech.backend_challenge.mappers.ExpenseToExpensesMapper;
import engagetech.backend_challenge.mappers.ExpensesToExpenseMapper;
import engagetech.backend_challenge.mappers.MapperException;
import io.dropwizard.hibernate.AbstractDAO;;

public class ExpensesDAO extends AbstractDAO<Expenses> {
	public ExpensesDAO(SessionFactory factory) {
		super(factory);
	}

	public List<Expense> listExpenses() {
		List<Expenses> expenses = this.list(query(Expenses.getSelectAllQuery()));
		// Convert db record into api resource
		List<Expense> result = null;
		try {
			ExpensesToExpenseMapper mapper = DataMapperFactory.createDataMapper(ExpensesToExpenseMapper.class);
			result = expenses.stream().map(e -> {
				return mapper.map(e);
			}).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	public void createExpense(Expense expense) throws MapperException {
		try {
			ExpenseToExpensesMapper mapper = DataMapperFactory.createDataMapper(ExpenseToExpensesMapper.class);
			persist(mapper.map(expense));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MapperException e) {
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
