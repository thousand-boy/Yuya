package app;

import util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreAnalyzerV2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = InputUtil.readInt(sc, "人数を入力してください: ");
        if (n <= 0) {
            System.out.println("人数は1以上で入力してください。");
            return; // closeしない（MenuAppに戻れなくなるため）
        }

        List<Integer> scores = inputScores(sc, n);
        printResult(scores);
    }

    // 点数入力（0〜100）
    static List<Integer> inputScores(Scanner sc, int n) {
        List<Integer> scores = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            int score = InputUtil.readScore(sc, i + "人目の点数を入力してください(0〜100): ");
            scores.add(score);
        }

        return scores;
    }

    static void printResult(List<Integer> scores) {
        int sum = sum(scores);
        double avg = (double) sum / scores.size();
        int max = max(scores);
        int min = min(scores);
        int passCount = passCount(scores, 60);

        System.out.println("合計: " + sum);
        System.out.println("平均: " + avg);
        System.out.println("最高: " + max);
        System.out.println("最低: " + min);
        System.out.println("合格(60以上)人数: " + passCount);
    }

    static int sum(List<Integer> scores) {
        int sum = 0;
        for (int s : scores) sum += s;
        return sum;
    }

    static int max(List<Integer> scores) {
        int max = scores.get(0);
        for (int s : scores) if (s > max) max = s;
        return max;
    }

    static int min(List<Integer> scores) {
        int min = scores.get(0);
        for (int s : scores) if (s < min) min = s;
        return min;
    }

    static int passCount(List<Integer> scores, int border) {
        int count = 0;
        for (int s : scores) if (s >= border) count++;
        return count;
    }
}