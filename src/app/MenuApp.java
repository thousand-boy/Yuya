package app;

import java.util.Scanner;

public class MenuApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();

            int choice = readInt(sc, "番号を選んでください: ");

            switch (choice) {
                case 1:
                    System.out.println("【1】app.MiniCashier を起動します");
                    MiniCashier.main(new String[0]);
                    pause(sc);
                    break;

                case 2:
                    System.out.println("【2】app.ScoreAnalyzer を起動します");
                    ScoreAnalyzer.main(new String[0]);
                    pause(sc);
                    break;

                case 3:
                    printHelp();
                    pause(sc);
                    break;

                case 4:
                    System.out.println("【4】app.ScoreAnalyzerV2 を起動します（人数可変）");
                    ScoreAnalyzerV2.main(new String[0]);
                    pause(sc);
                    break;

                case 5:
                    System.out.println("【5】app.StudentApp を起動します（名前＋点数）");
                    StudentApp.main(new String[0]);
                    pause(sc);
                    break;

                case 0:
                    System.out.println("終了します。おつかれさまでした！");
                    sc.close();
                    return;

                default:
                    System.out.println("0 / 1 / 2 / 3 / 4 / 5 のどれかを入力してください。");
                    pause(sc);
                    break;
            }

            System.out.println();
        }
    }

    static void printMenu() {
        System.out.println("==== メニュー ====");
        System.out.println("1: 入力式レジ（app.MiniCashier）");
        System.out.println("2: 点数集計（app.ScoreAnalyzer）");
        System.out.println("3: このアプリについて（ヘルプ）");
        System.out.println("4: 点数集計V2（人数可変）");
        System.out.println("5: 学生点数管理（app.StudentApp）");
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
        System.out.println("- app.MenuApp から app.MiniCashier / app.ScoreAnalyzer を起動できます");
        System.out.println("- 入力が数字以外でも落ちないようにしています");
        System.out.println("- 終了後はEnterでメニューに戻ります");
    }
}