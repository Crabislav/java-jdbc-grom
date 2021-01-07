package hibernate.lesson6.productdaohql;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

//        System.out.println(productDAO.findById(49));
//        System.out.println(productDAO.findByName("single"));
//        System.out.println(productDAO.findByContainedName("tes"));
//        System.out.println(productDAO.findByPrice(30, 10));
//        System.out.println(productDAO.findByNameSortedAsc("prod"));
//        System.out.println(productDAO.findByNameSortedDesc("prod"));
        System.out.println(productDAO.findByPriceSortedDesc(30, 10));
    }
}
