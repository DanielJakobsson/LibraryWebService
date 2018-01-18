package eagle.library.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

public interface Dao<T> {

	void create(T bean);

	void update(T bean);

	Optional<T> find(long id);
	
	List<T> findByQuery(TypedQuery<T> query);

	List<T> listAll();

}
