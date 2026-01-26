import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScoreDistribution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = readInt(sc, "人数を入力してください: ");
        if (n <= 0) {
            System.out.println("人数は1以上で入力してください。");
            return;
        }

        Map<String, Integer> counts = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            int score = readScore(sc, i);

            String grade = toGrade(score);

            // gradeの人数を+1する（なければ0から）
            counts.put(grade, counts.getOrDefault(grade, 0) + 1);
        }

        printCounts(counts);
        // sc.close(); はしない（統合を考える）
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

    static int readScore(Scanner sc, int index) {
        while (true) {
            int score = readInt(sc, index + "人目の点数を入力してください(0〜100): ");
            if (score >= 0 && score <= 100) return score;
            System.out.println("点数は0〜100で入力してください。");
        }
    }

    static String toGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    static void printCounts(Map<String, Integer> counts) {
        System.out.println("---- 成績分布 ----");
        System.out.println("A(90-100): " + counts.getOrDefault("A", 0));
        System.out.println("B(80-89):  " + counts.getOrDefault("B", 0));
        System.out.println("C(70-79):  " + counts.getOrDefault("C", 0));
        System.out.println("D(60-69):  " + counts.getOrDefault("D", 0));
        System.out.println("F(0-59):   " + counts.getOrDefault("F", 0));
    }
}