package jdbc.lesson4.homework.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO<T> {
    static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    static final String USER = "main";
    static final String PASSWORD = "gromcode";

    public abstract T save(T t) throws SQLException;

    public abstract void delete(long id) throws SQLException;

    public abstract T update(T t) throws SQLException;

    public abstract T findById(long id) throws SQLException;

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
