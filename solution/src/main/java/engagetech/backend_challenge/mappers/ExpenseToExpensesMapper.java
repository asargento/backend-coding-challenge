package engagetech.backend_challenge.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import engagetech.backend_challenge.backend_challengeConfiguration;
import engagetech.backend_challenge.api.Expense;
import engagetech.backend_challenge.core.Expenses;

public class ExpenseToExpensesMapper extends AbstractDataMapper<Expense, Expenses> { 
	public ExpenseToExpensesMapper(backend_challengeConfiguration configuration) {
		super(configuration);
	}

	@Override
	public Expenses map(Expense e) throws MapperException {
		Expenses expense = new Expenses();
		expense.setId(e.getId());
		SimpleDateFormat formatter = new SimpleDateFormat(getDateFormat());
		try {
			expense.setDate(formatter.parse(e.getDate()));
		} catch (ParseException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			throw new MapperException(exception);
		}
		expense.setReason(e.getReason());
		expense.setAmount(e.getAmount());
		return expense;
	}
}
