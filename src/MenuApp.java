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

                case 3:
                    printHelp();
                    pause(sc);
                    break;

                case 0:
                    System.out.println("終了します。おつかれさまでした！");
                    sc.close();
                    return;

                default:
                    System.out.println("0 / 1 / 2 / 3 のどれかを入力してください。");
                    pause(sc);
                    break;
            }

            System.out.println();
        }
    }

    static void printMenu() {
        System.out.println("==== メニュー ====");
        System.out.println("1: 入力式レジ（MiniCashier）");
        System.out.println("2: 点数集計（ScoreAnalyzer）");
        System.out.println("3: このアプリについて（ヘルプ）");
        System.out.println("0: 終了");
        System.out.println("===============");
    }

    static int readInt(Scanner sc, String message) {
        System.out.print(message);

        while (!sc.hasNextInt()) {
            System.out.println("数字を入力してください。");
            sc.next();
            System.out.print(message);
        }

        int value = sc.nextInt();
        sc.nextLine(); // 改行を捨てる
        return value;
    }

    static void pause(Scanner sc) {
        System.out.println("\nEnterでメニューに戻ります...");
        sc.nextLine();
    }

    static void printHelp() {
        System.out.println("==== このアプリについて ====");
        System.out.println("- MenuApp から MiniCashier / ScoreAnalyzer を起動できます");
        System.out.println("- 入力が数字以外でも落ちないようにしています");
        System.out.println("- 終了後はEnterでメニューに戻ります");
    }
}