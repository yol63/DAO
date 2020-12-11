import java.sql.SQLException;
import java.util.List;

public interface DAO<T,K> {
    public T getEntityById(K id) throws SQLException;
    public T update(T entity) throws SQLException;
    public List<T> getAll() throws SQLException;
    public boolean delete(K id) throws SQLException;
    public boolean create(T entity) throws SQLException;
}
