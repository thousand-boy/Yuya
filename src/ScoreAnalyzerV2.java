import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreAnalyzerV2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== ScoreAnalyzerV2（人数可変）===");

        int n = readInt(sc, "人数を入力してください: ");

        if (n <= 0) {
            System.out.println("人数は1以上で入力してください。");
            return; // closeはしない（MenuApp統合を考慮）
        }

        List<Integer> scores = readScores(sc, n);

        Result r = analyze(scores);
        printResult(r);
    }

    // 数字以外でも落ちない入力（MenuAppのreadIntと同じ思想）
    static int readInt(Scanner sc, String message) {
        System.out.print(message);

        while (!sc.hasNextInt()) {
            System.out.println("数字を入力してください。");
            sc.next(); // 変な入力を捨てる
            System.out.print(message);
        }

        int value = sc.nextInt();
        sc.nextLine(); // 改行を捨てる（次のnextLine対策）
        return value;
    }

    static List<Integer> readScores(Scanner sc, int n) {
        List<Integer> scores = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            int score = readInt(sc, i + "人目の点数を入力してください: ");

            // ここはルール自由：0〜100に制限したいならチェック
            if (score < 0 || score > 100) {
                System.out.println("点数は0〜100で入力してください。もう一度。");
                i--; // この人の分をやり直す
                continue;
            }

            scores.add(score);
        }

        return scores;
    }

    static Result analyze(List<Integer> scores) {
        int sum = 0;
        int max = scores.get(0);
        int min = scores.get(0);
        int passCount = 0;

        for (int s : scores) {
            sum += s;
            if (s > max) max = s;
            if (s < min) min = s;
            if (s >= 60) passCount++;
        }

        double avg = (double) sum / scores.size();
        return new Result(sum, avg, max, min, passCount, scores.size());
    }

    static void printResult(Result r) {
        System.out.println("---- 結果 ----");
        System.out.println("人数: " + r.count);
        System.out.println("合計: " + r.sum);
        System.out.println("平均: " + r.avg);
        System.out.println("最高: " + r.max);
        System.out.println("最低: " + r.min);
        System.out.println("合格(60以上)人数: " + r.passCount);
    }

    static class Result {
        int sum;
        double avg;
        int max;
        int min;
        int passCount;
        int count;

        Result(int sum, double avg, int max, int min, int passCount, int count) {
            this.sum = sum;
            this.avg = avg;
            this.max = max;
            this.min = min;
            this.passCount = passCount;
            this.count = count;
        }
    }
}