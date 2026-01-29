package service;

import model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    public static int sum(List<Student> students) {
        int sum = 0;
        for (Student s : students) sum += s.getScore();
        return sum;
    }

    public static double average(List<Student> students) {
        return (double) sum(students) / students.size();
    }

    public static int maxScore(List<Student> students) {
        int max = students.get(0).getScore();
        for (Student s : students) if (s.getScore() > max) max = s.getScore();
        return max;
    }

    public static int minScore(List<Student> students) {
        int min = students.get(0).getScore();
        for (Student s : students) if (s.getScore() < min) min = s.getScore();
        return min;
    }

    public static int passCount(List<Student> students) {
        int count = 0;
        for (Student s : students) if (s.isPass()) count++;
        return count;
    }

    // 名前部分一致（大小文字を無視）
    public static int searchAndPrint(List<Student> students, String keyword) {
        String k = keyword.toLowerCase();
        int hit = 0;

        for (Student s : students) {
            if (s.getName().toLowerCase().contains(k)) {
                System.out.println(s.getName() + " : " + s.getScore() + "点 (" + s.grade() + ")");
                hit++;
            }
        }
        return hit;
    }

    // 成績分布（A/B/C/D/F）
    public static Map<String, Integer> gradeDistribution(List<Student> students) {
        Map<String, Integer> counts = new HashMap<>();
        for (Student s : students) {
            String g = s.grade();
            counts.put(g, counts.getOrDefault(g, 0) + 1);
        }
        return counts;
    }
}