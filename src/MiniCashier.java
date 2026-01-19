import java.util.Scanner;

public class MiniCashier {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("商品金額を入力してください: ");
        int price = sc.nextInt();

        System.out.print("支払額を入力してください: ");
        int paid = sc.nextInt();

        System.out.println("商品金額: " + price + "円");
        System.out.println("支払額: " + paid + "円");

        if (paid >= price) {
            int change = paid - price;
            System.out.println("お釣り: " + change + "円");
        } else {
            int shortage = price - paid;
            System.out.println("あと " + shortage + "円 足りません");
        }

        sc.close();
    }
}