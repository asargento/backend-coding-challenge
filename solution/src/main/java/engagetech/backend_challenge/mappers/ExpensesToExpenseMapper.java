package engagetech.backend_challenge.mappers;

import java.text.SimpleDateFormat;

import engagetech.backend_challenge.backend_challengeConfiguration;
import engagetech.backend_challenge.api.Expense;
import engagetech.backend_challenge.core.Expenses;

public class ExpensesToExpenseMapper extends AbstractDataMapper<Expenses, Expense> {
	public ExpensesToExpenseMapper(backend_challengeConfiguration configuration) {
		super(configuration);
	}

	@Override
	public Expense map(Expenses expense) {
		SimpleDateFormat formatter = new SimpleDateFormat(getDateFormat());
		return new Expense(expense.getId(), 
				formatter.format(expense.getDate()),
				expense.getReason(), expense.getAmount());
	}
}
