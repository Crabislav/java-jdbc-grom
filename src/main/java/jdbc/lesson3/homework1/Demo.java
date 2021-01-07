package jdbc.lesson3.homework1;

public class Demo {
    public static void main(String[] args) throws BadRequestException {
        Solution solution = new Solution();
//        System.out.println(solution.findProductsWithEmptyDescription());
//        System.out.println(solution.findProductsByPrice(400, 100));
        System.out.println(solution.findProductsByName("rod"));
    }
}
