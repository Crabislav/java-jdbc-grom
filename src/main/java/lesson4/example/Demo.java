package lesson4.example;

import java.util.ArrayList;
import java.util.List;


public class Demo {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        int amountToGenerate = 5;

        for (int i = 1; i <= amountToGenerate; i++) {
            products.add(new Product(i + 20L, "Product" + (i + 20L), "test", 500));
        }

        TransactionDemo.save(products);
    }
}
