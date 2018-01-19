package engagetech.backend_challenge.mappers;

import engagetech.backend_challenge.backend_challengeConfiguration;

public class DataMapperFactory {
	static public backend_challengeConfiguration configuration;
	
	public static <T> T createDataMapper(Class<T> t) throws InstantiationException, IllegalAccessException {
		if (t.getName() == ExpensesToExpenseMapper.class.getName()) {
			return (T) new ExpensesToExpenseMapper(configuration);
		} else if (t.getName() == ExpenseToExpensesMapper.class.getName()) {
			return (T) new ExpenseToExpensesMapper(configuration);
		} 
		return null;
	}
}
