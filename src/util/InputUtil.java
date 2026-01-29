package util;

import java.util.Scanner;

public class InputUtil {

    // 数字入力（数字以外なら繰り返し）
    public static int readInt(Scanner sc, String message) {
        System.out.print(message);

        while (!sc.hasNextInt()) {
            System.out.println("数字を入力してください。");
            sc.next();
            System.out.print(message);
        }

        int v = sc.nextInt();
        sc.nextLine(); // 改行消費（重要）
        return v;
    }

    // 空文字を許さない文字入力
    public static String readNonEmptyLine(Scanner sc, String message) {
        System.out.print(message);
        String s = sc.nextLine().trim();

        while (s.isEmpty()) {
            System.out.println("空では入力できません。もう一度。");
            System.out.print(message);
            s = sc.nextLine().trim();
        }
        return s;
    }

    // 点数入力（0〜100）
    public static int readScore(Scanner sc, String message) {
        while (true) {
            int score = readInt(sc, message);
            if (0 <= score && score <= 100) return score;
            System.out.println("点数は0〜100で入力してください。");
        }
    }

    // Enter待ち
    public static void pause(Scanner sc, String message) {
        System.out.println("\n" + message);
        sc.nextLine();
    }

    // よく使う版
    public static void pause(Scanner sc) {
        pause(sc, "Enterで続行します...");
    }
}