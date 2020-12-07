package lesson3.homework1;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface StatementPreparer {
    void prepare(PreparedStatement preparedStatement);
}
