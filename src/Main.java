public class Main {
    public static void main(String[] args) {

        int price = 680;
        int paid = 680;   // ← まず不足の例

        System.out.println("商品金額: " + price + "円");
        System.out.println("支払額: " + paid + "円");

        if (paid >= price) {
            int change = paid - price;
            System.out.println("お釣り: " + change + "円");
        } else {
            int shortage = price - paid;
            System.out.println("あと " + shortage + "円 足りません");
        }
    }
}