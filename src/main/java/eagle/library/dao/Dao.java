package eagle.library.dao;

import java.util.List;

import javax.persistence.TypedQuery;

public interface Dao<T> {

	void create(T bean);

	void update(T bean);

	List<T> findByQuery(TypedQuery<T> query);

	List<T> listAll();

}
