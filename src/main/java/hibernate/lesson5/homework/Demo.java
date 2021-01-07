package hibernate.lesson5.homework;

import hibernate.lesson5.example.Product;
import hibernate.lesson5.homework.repository.ProductRepository;

public class Demo {
    private static final ProductRepository PRODUCT_REPOSITORY = new ProductRepository();

    public static void main(String[] args) {
        Product product = new Product();
        product.setId(2);
        product.setName("test");
        product.setDescription("test descr");
        product.setPrice(1);


//        PRODUCT_REPOSITORY.save(product);
//        PRODUCT_REPOSITORY.delete(product.getId());
        product.setName("asdsad");
        PRODUCT_REPOSITORY.update(product);
    }
}
