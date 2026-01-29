package app;

import java.util.Scanner;

public class ScoreAnalyzer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] scores = readScores(sc, 5);
        Result r = analyze(scores);
        printResult(r);

    }

    // ① 入力：点数を配列に入れる
    static int[] readScores(Scanner sc, int n) {
        int[] scores = new int[n];
        for (int i = 0; i < scores.length; i++) {
            System.out.print((i + 1) + "人目の点数を入力してください: ");
            scores[i] = sc.nextInt();
        }
        return scores;
    }

    // ② 集計：合計/平均/最大/最小/合格人数
    static Result analyze(int[] scores) {
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
        return new Result(sum, avg, max, min, passCount);
    }

    // ③ 表示：結果を出す
    static void printResult(Result r) {
        System.out.println("合計: " + r.sum);
        System.out.println("平均: " + r.avg);
        System.out.println("最高: " + r.max);
        System.out.println("最低: " + r.min);
        System.out.println("合格(60以上)人数: " + r.passCount);
    }

    // 結果まとめ用（小さな箱）
    static class Result {
        int sum;
        double avg;
        int max;
        int min;
        int passCount;

        Result(int sum, double avg, int max, int min, int passCount) {
            this.sum = sum;
            this.avg = avg;
            this.max = max;
            this.min = min;
            this.passCount = passCount;
        }
    }
}