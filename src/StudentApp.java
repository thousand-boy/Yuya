import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = readInt(sc, "人数を入力してください: ");
        if (n <= 0) {
            System.out.println("人数は1以上で入力してください。");
            return;
        }

        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String name = readName(sc, i + "人目の名前を入力してください: ");
            int score = readScore(sc, i + "人目の点数を入力してください(0〜100): ");
            students.add(new Student(name, score));
        }

        printAll(students);
        printSummary(students);
        printPassList(students);

        // 統合を考えて close はしない
    }

    static int readInt(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("数字を入力してください。");
            sc.next();
            System.out.print(message);
        }
        int v = sc.nextInt();
        sc.nextLine(); // 改行消費
        return v;
    }

    static String readName(Scanner sc, String message) {
        System.out.print(message);
        String name = sc.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("名前が空です。もう一度。");
            System.out.print(message);
            name = sc.nextLine().trim();
        }
        return name;
    }

    static int readScore(Scanner sc, String message) {
        while (true) {
            int score = readInt(sc, message);
            if (0 <= score && score <= 100) return score;
            System.out.println("点数は0〜100で入力してください。");
        }
    }

    static void printAll(List<Student> students) {
        System.out.println("---- 一覧 ----");
        for (Student s : students) {
            System.out.println(s); // toString() が呼ばれる
        }
    }

    static void printSummary(List<Student> students) {
        int sum = 0;
        int max = students.get(0).getScore();
        int min = students.get(0).getScore();

        for (Student s : students) {
            int score = s.getScore();
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }

        double avg = (double) sum / students.size();

        System.out.println("---- 集計 ----");
        System.out.println("人数: " + students.size());
        System.out.println("合計: " + sum);
        System.out.println("平均: " + avg);
        System.out.println("最高: " + max);
        System.out.println("最低: " + min);
    }

    static void printPassList(List<Student> students) {
        System.out.println("---- 合格者(60以上) ----");
        int count = 0;

        for (Student s : students) {
            if (s.isPass()) {
                System.out.println(s.getName() + " (" + s.getScore() + "点)");
                count++;
            }
        }

        System.out.println("合格人数: " + count);
    }
}