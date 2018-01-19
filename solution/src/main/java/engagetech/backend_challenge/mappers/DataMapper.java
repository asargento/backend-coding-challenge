package engagetech.backend_challenge.mappers;

public interface DataMapper<T1, T2> {
	public T2 map(T1 t1) throws MapperException;
}
