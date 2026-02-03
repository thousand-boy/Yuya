package service;

import model.Student;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentCsvServiceTest {

    @Test
    void load_shouldSkipInvalidLines_andLoadValidStudents() throws Exception {
        // 一時CSVを作る（ヘッダー＋有効3件＋無効いろいろ）
        Path temp = Files.createTempFile("students_test_", ".csv");

        List<String> lines = List.of(
                "name,score",
                "Alice,100",
                "Bob,50",
                "",              // 空行（スキップ）
                "Aki,80",
                "NoScore,",      // 点数欠け（スキップ）
                ",90",           // 名前欠け（スキップ）
                "Ken,abc",       // 数字じゃない（スキップ）
                "Over,101"       // 範囲外（スキップ）
        );
        Files.write(temp, lines, StandardCharsets.UTF_8);

        try {
            // 実行
            List<Student> students = StudentCsvService.load(temp.toString());

            // 有効な3件だけ読めているはず
            assertEquals(3, students.size());

            assertEquals("Alice", students.get(0).getName());
            assertEquals(100, students.get(0).getScore());

            assertEquals("Bob", students.get(1).getName());
            assertEquals(50, students.get(1).getScore());

            assertEquals("Aki", students.get(2).getName());
            assertEquals(80, students.get(2).getScore());
        } finally {
            Files.deleteIfExists(temp);
        }
    }
}