package app;

import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = readInt(sc, "人数を入力してください: ");
        if (n <= 0) {
            System.out.println("人数は1以上で入力してください。");
            return; // closeしない（MenuApp統合を考慮）
        }

        List<Student> students = readStudents(sc, n);

        while (true) {
            printStudentMenu();
            int choice = readInt(sc, "番号を選んでください: ");

            switch (choice) {
                case 1:
                    printAll(students);
                    pause(sc);
                    break;

                case 2:
                    printSummary(students);
                    pause(sc);
                    break;

                case 3:
                    printPassList(students);
                    pause(sc);
                    break;

                case 4:
                    searchByName(students, sc);
                    pause(sc);
                    break;

                case 5:
                    printGradeDistribution(students);
                    pause(sc);
                    break;

                case 0:
                    System.out.println("StudentAppを終了します。");
                    return;

                default:
                    System.out.println("0 / 1 / 2 / 3 / 4 / 5 のどれかを入力してください。");
                    pause(sc);
                    break;
            }
        }
    }

    // ---------- 入力 ----------

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

    static String readNonEmptyLine(Scanner sc, String message) {
        System.out.print(message);
        String s = sc.nextLine().trim();

        while (s.isEmpty()) {
            System.out.println("空では入力できません。もう一度。");
            System.out.print(message);
            s = sc.nextLine().trim();
        }
        return s;
    }

    static int readScore(Scanner sc, String message) {
        while (true) {
            int score = readInt(sc, message);
            if (0 <= score && score <= 100) return score;
            System.out.println("点数は0〜100で入力してください。");
        }
    }

    static List<Student> readStudents(Scanner sc, int n) {
        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String name = readNonEmptyLine(sc, i + "人目の名前を入力してください: ");
            int score = readScore(sc, i + "人目の点数を入力してください(0〜100): ");
            students.add(new Student(name, score));
        }

        return students;
    }

    // ---------- メニュー ----------

    static void printStudentMenu() {
        System.out.println("\n==== app.StudentApp メニュー ====");
        System.out.println("1: 一覧表示");
        System.out.println("2: 集計（合計/平均/最高/最低）");
        System.out.println("3: 合格者一覧（60以上）");
        System.out.println("4: 名前で検索");
        System.out.println("5: 成績分布（A/B/C/D/F）");
        System.out.println("0: 戻る");
        System.out.println("============================");
    }

    static void pause(Scanner sc) {
        System.out.println("\nEnterで続行します...");
        sc.nextLine();
    }

    // ---------- 表示/処理 ----------

    static void printAll(List<Student> students) {
        System.out.println("---- 一覧 ----");
        for (Student s : students) {
            System.out.println(s); // model.Student.toString()
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
                System.out.println(s.getName() + " (" + s.getScore() + "点 / " + s.grade() + ")");
                count++;
            }
        }

        System.out.println("合格人数: " + count);
    }

    static void searchByName(List<Student> students, Scanner sc) {
        String keyword = readNonEmptyLine(sc, "検索する名前（部分一致OK）: ").toLowerCase();

        System.out.println("---- 検索結果 ----");
        int hit = 0;

        for (Student s : students) {
            String nameLower = s.getName().toLowerCase();
            if (nameLower.contains(keyword)) {
                System.out.println(s.getName() + " : " + s.getScore() + "点 (" + s.grade() + ")");
                hit++;
            }
        }

        if (hit == 0) {
            System.out.println("見つかりませんでした。");
        } else {
            System.out.println("件数: " + hit);
        }
    }

    // ★追加：成績分布（Map集計）
    static void printGradeDistribution(List<Student> students) {
        Map<String, Integer> counts = new HashMap<>();

        for (Student s : students) {
            String g = s.grade(); // A/B/C/D/F
            counts.put(g, counts.getOrDefault(g, 0) + 1);
        }

        System.out.println("---- 成績分布 ----");
        System.out.println("A(90-100): " + counts.getOrDefault("A", 0));
        System.out.println("B(80-89):  " + counts.getOrDefault("B", 0));
        System.out.println("C(70-79):  " + counts.getOrDefault("C", 0));
        System.out.println("D(60-69):  " + counts.getOrDefault("D", 0));
        System.out.println("F(0-59):   " + counts.getOrDefault("F", 0));
    }
}