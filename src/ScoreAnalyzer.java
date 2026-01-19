import java.util.Scanner;

public class ScoreAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] scores = new int[5];

        for (int i = 0; i < scores.length; i++) {
            System.out.print((i + 1) + "人目の点数を入力してください: ");
            scores[i] = sc.nextInt();
        }

        int sum = 0;
        int max = scores[0];
        int min = scores[0];
        int passCount = 0;

        for (int s : scores) {
            sum += s;
            if (s > max) max = s;
            if (s < min) min = s;
            if (s >= 60) passCount++;
        }

        double avg = (double) sum / scores.length;

        System.out.println("合計: " + sum);
        System.out.println("平均: " + avg);
        System.out.println("最高: " + max);
        System.out.println("最低: " + min);
        System.out.println("合格(60以上)人数: " + passCount);

        sc.close();
    }
}