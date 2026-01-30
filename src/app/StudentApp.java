package app;

import model.Student;
import service.StudentCsvService;
import service.StudentService;
import util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String csvPath = "data/students.csv";

        // 起動時にCSVがあれば読み込み提案
        List<Student> loadedOnStart = StudentCsvService.load(csvPath);

        List<Student> students;

        if (!loadedOnStart.isEmpty()) {
            System.out.println("\nCSVに保存された学生データが見つかりました（" + loadedOnStart.size() + "件）。");
            boolean useCsv = InputUtil.readYesNo(sc, "このデータを使用しますか？ (y/n): ");

            if (useCsv) {
                students = loadedOnStart;
                System.out.println("CSVのデータで開始します。");
            } else {
                System.out.println("手入力で開始します。");
                students = inputStudents(sc);
            }
        } else {
            // ファイルなし or 0件 → 手入力
            students = inputStudents(sc);
        }

        while (true) {
            printStudentMenu();
            int choice = InputUtil.readInt(sc, "番号を選んでください: ");

            switch (choice) {
                case 1:
                    printAll(students);
                    InputUtil.pause(sc);
                    break;

                case 2:
                    printSummary(students);
                    InputUtil.pause(sc);
                    break;

                case 3:
                    printPassList(students);
                    InputUtil.pause(sc);
                    break;

                case 4:
                    searchByName(students, sc);
                    InputUtil.pause(sc);
                    break;

                case 5:
                    printGradeDistribution(students);
                    InputUtil.pause(sc);
                    break;

                case 6:
                    StudentCsvService.save(students, csvPath);
                    InputUtil.pause(sc);
                    break;

                case 7:
                    List<Student> loadedNow = StudentCsvService.load(csvPath);
                    if (loadedNow.isEmpty()) {
                        System.out.println("読み込み結果が0件のため、上書きしませんでした。");
                    } else {
                        students = loadedNow; // 上書き
                        System.out.println("学生データをCSVの内容で更新しました。");
                    }
                    InputUtil.pause(sc);
                    break;

                case 0:
                    System.out.println("StudentAppを終了します。");
                    // 統合アプリ（MenuApp）から戻れるように close はしない
                    return;

                default:
                    System.out.println("0 / 1 / 2 / 3 / 4 / 5 / 6 / 7 のどれかを入力してください。");
                    InputUtil.pause(sc);
                    break;
            }
        }
    }

    // ---------- 起動時の手入力 ----------

    static List<Student> inputStudents(Scanner sc) {
        int n = InputUtil.readInt(sc, "人数を入力してください: ");
        while (n <= 0) {
            System.out.println("人数は1以上で入力してください。");
            n = InputUtil.readInt(sc, "人数を入力してください: ");
        }
        return readStudents(sc, n);
    }

    // ---------- 入力（InputUtilを使用） ----------

    static List<Student> readStudents(Scanner sc, int n) {
        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String name = InputUtil.readNonEmptyLine(sc, i + "人目の名前を入力してください: ");
            int score = InputUtil.readScore(sc, i + "人目の点数を入力してください(0〜100): ");
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
        System.out.println("6: CSVに保存（data/students.csv / 自動バックアップあり）");
        System.out.println("7: CSVから読み込み（data/students.csvで上書き）");
        System.out.println("0: 戻る");
        System.out.println("============================");
    }

    // ---------- 表示/処理 ----------

    static void printAll(List<Student> students) {
        System.out.println("---- 一覧 ----");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    static void printSummary(List<Student> students) {
        int sum = StudentService.sum(students);
        double avg = StudentService.average(students);
        int max = StudentService.maxScore(students);
        int min = StudentService.minScore(students);

        System.out.println("---- 集計 ----");
        System.out.println("人数: " + students.size());
        System.out.println("合計: " + sum);
        System.out.println("平均: " + avg);
        System.out.println("最高: " + max);
        System.out.println("最低: " + min);
    }

    static void printPassList(List<Student> students) {
        System.out.println("---- 合格者(60以上) ----");

        for (Student s : students) {
            if (s.isPass()) {
                System.out.println(s.getName() + " (" + s.getScore() + "点 / " + s.grade() + ")");
            }
        }

        int count = StudentService.passCount(students);
        System.out.println("合格人数: " + count);
    }

    static void searchByName(List<Student> students, Scanner sc) {
        String keyword = InputUtil.readNonEmptyLine(sc, "検索する名前（部分一致OK）: ");

        System.out.println("---- 検索結果 ----");
        int hit = StudentService.searchAndPrint(students, keyword);

        if (hit == 0) {
            System.out.println("見つかりませんでした。");
        } else {
            System.out.println("件数: " + hit);
        }
    }

    static void printGradeDistribution(List<Student> students) {
        Map<String, Integer> counts = StudentService.gradeDistribution(students);

        System.out.println("---- 成績分布 ----");
        System.out.println("A(90-100): " + counts.getOrDefault("A", 0));
        System.out.println("B(80-89):  " + counts.getOrDefault("B", 0));
        System.out.println("C(70-79):  " + counts.getOrDefault("C", 0));
        System.out.println("D(60-69):  " + counts.getOrDefault("D", 0));
        System.out.println("F(0-59):   " + counts.getOrDefault("F", 0));
    }
}