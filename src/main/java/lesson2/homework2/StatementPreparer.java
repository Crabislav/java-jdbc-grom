package lesson2.homework2;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementPreparer {
    void prepare(PreparedStatement preparedStatement, Product product) throws SQLException;
}
