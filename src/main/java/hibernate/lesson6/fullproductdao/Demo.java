package hibernate.lesson6.fullproductdao;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    private static final ProductDAO productDAO = ProductDAO.getInstance();

    public static void main(String[] args) {
        Product product = generateProduct("single", "descr", 50);
        //save test +
//       productDAO.save(product);

        //update test +
//        product.setPrice(60);
//       productDAO.update(product);

        //delete test +
//       productDAO.delete(47);

        List<Product> products = generateProducts(2, "list", "test", 5);
        //saveAll test +
//        productDAO.saveAll(products);
//        products.forEach(product1 -> product1.setName("updateAll"));

        //updateAll test +
//        productDAO.updateAll(products);

        //deleteAll test +
        int id = 55;
        for (Product product1 : products) {
            product1.setId(id);
            id += 2;
        }
        productDAO.deleteAll(products);
    }

    private static List<Product> generateProducts(int amount, String name, String description, int price) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            products.add(generateProduct(name, description, price));
        }
        return products;
    }

    private static Product generateProduct(String name, String description, int price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }
}
