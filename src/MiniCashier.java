import java.util.Scanner;

public class MiniCashier {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int price = readInt(sc, "商品金額を入力してください: ");
            int paid  = readInt(sc, "支払額を入力してください: ");

            if (!isValid(price, paid)) {
                return;
            }

            printHeader(price, paid);
            printResult(price, paid);

        } catch (Exception e) {
            System.out.println("数字を入力してください（例：680）");
        } finally {
            sc.close();
        }
    }

    static int readInt(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextInt();
    }

    static boolean isValid(int price, int paid) {
        if (price <= 0) {
            System.out.println("金額が不正です（商品金額は1円以上）");
            return false;
        }
        if (paid < 0) {
            System.out.println("金額が不正です（支払額は0円以上）");
            return false;
        }
        return true;
    }

    static void printHeader(int price, int paid) {
        System.out.println("商品金額: " + price + "円");
        System.out.println("支払額: " + paid + "円");
    }

    static void printResult(int price, int paid) {
        if (paid >= price) {
            int change = paid - price;
            System.out.println("お釣り: " + change + "円");
        } else {
            int shortage = price - paid;
            System.out.println("あと " + shortage + "円 足りません");
        }
    }
}