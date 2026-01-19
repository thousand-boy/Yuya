public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        int price = 680;
        int paid = 1000;
        int change = paid - price;

        System.out.println("商品金額: " + price + "円");
        System.out.println("支払額: " + paid + "円");
        System.out.println("お釣り: " + change + "円");
    }
}