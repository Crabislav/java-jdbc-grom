package lesson2.homework2;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface StatementPreparer {
    void prepare(PreparedStatement preparedStatement, Product product);
}
