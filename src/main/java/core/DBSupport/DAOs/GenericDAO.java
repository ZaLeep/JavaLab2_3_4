package core.DBSupport.DAOs;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

    boolean insert(T object) throws SQLException;

    T read(int id) throws SQLException;

    boolean update(T object) throws SQLException;

    boolean delete(T object) throws SQLException;

    List<T> getAll() throws SQLException;
}
