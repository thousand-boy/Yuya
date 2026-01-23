import java.util.Scanner;

public class MenuApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();

            int choice = readInt(sc, "番号を選んでください: ");

            switch (choice) {
                case 1:
                    System.out.println("【1】MiniCashier を起動します");
                    MiniCashier.main(new String[0]);
                    pause(sc);
                    break;

                case 2:
                    System.out.println("【2】ScoreAnalyzer を起動します");
                    ScoreAnalyzer.main(new String[0]);
                    pause(sc);
                    break;

                case 0:
                    System.out.println("終了します。おつかれさまでした！");
                    sc.close();
                    return;
                default:
                    System.out.println("0 / 1 / 2 のどれかを入力してください。");
                    break;
            }

            System.out.println(); // 空行（見やすくする）
        }
    }

    static void printMenu() {
        System.out.println("==== メニュー ====");
        System.out.println("1: 入力式レジ（MiniCashier）");
        System.out.println("2: 点数集計（ScoreAnalyzer）");
        System.out.println("0: 終了");
        System.out.println("===============");
    }

    static int readInt(Scanner sc, String message) {
        System.out.print(message);

        while (!sc.hasNextInt()) {
            System.out.println("数字を入力してください。");
            sc.next(); // 数字じゃない入力を捨てる
            System.out.print(message);
        }

        int value = sc.nextInt(); // 数字を読む
        sc.nextLine();            // その後ろの改行を捨てる
        return value;
    }

    static void pause(Scanner sc) {
        System.out.println("\nEnterでメニューに戻ります...");
        sc.nextLine(); // nextInt() の残り改行を捨てる
        sc.nextLine(); // Enter待ち
    }
}