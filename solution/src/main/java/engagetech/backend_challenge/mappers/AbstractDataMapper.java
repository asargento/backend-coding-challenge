package engagetech.backend_challenge.mappers;

import engagetech.backend_challenge.backend_challengeConfiguration;

public abstract class AbstractDataMapper<T, T1> implements DataMapper<T, T1> {
	private String dateFormat;
	
	public AbstractDataMapper(backend_challengeConfiguration configuration) {
		this.dateFormat = configuration.getDateFormat();
	}
	
	protected String getDateFormat() {
		return dateFormat;
	}
	
	abstract public T1 map(T t1) throws MapperException;
}
