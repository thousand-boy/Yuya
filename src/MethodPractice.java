public class MethodPractice {

    public static void main(String[] args) {

        int price = 680;
        int paid = 1000;

        printHeader(price, paid);

        if (paid >= price) {
            int change = calculateChange(price, paid);
            printChange(change);
        } else {
            int shortage = calculateShortage(price, paid);
            printShortage(shortage);
        }
    }

    static void printHeader(int price, int paid) {
        System.out.println("商品金額: " + price + "円");
        System.out.println("支払額: " + paid + "円");
    }

    static int calculateChange(int price, int paid) {
        return paid - price;
    }

    static int calculateShortage(int price, int paid) {
        return price - paid;
    }

    static void printChange(int change) {
        System.out.println("お釣り: " + change + "円");
    }

    static void printShortage(int shortage) {
        System.out.println("あと " + shortage + "円 足りません");
    }
}