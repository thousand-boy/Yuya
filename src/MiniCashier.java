import java.util.Scanner;

public class MiniCashier {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("商品金額を入力してください: ");
            int price = sc.nextInt();

            System.out.print("支払額を入力してください: ");
            int paid = sc.nextInt();

            // 入力チェック（バリデーション）
            if (price <= 0) {
                System.out.println("金額が不正です（商品金額は1円以上）");
                return;
            }
            if (paid < 0) {
                System.out.println("金額が不正です（支払額は0円以上）");
                return;
            }

            System.out.println("商品金額: " + price + "円");
            System.out.println("支払額: " + paid + "円");

            if (paid >= price) {
                int change = paid - price;
                System.out.println("お釣り: " + change + "円");
            } else {
                int shortage = price - paid;
                System.out.println("あと " + shortage + "円 足りません");
            }

        } catch (Exception e) {
            System.out.println("数字を入力してください（例：680）");
        } finally {
            sc.close();
        }
    }
}