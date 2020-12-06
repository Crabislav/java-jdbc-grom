package lesson2.homework2;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface DataProcessor {
    void process(PreparedStatement preparedStatement, Product product);
}
