package service;

import model.Student;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class StudentCsvServiceSaveTest {

    @Test
    void save_shouldCreateBackup_andKeepOnlyLatest5() throws Exception {
        // テスト用ディレクトリ
        Path dir = Files.createTempDirectory("csv_save_test_");
        Path csv = dir.resolve("students.csv");

        try {
            // 初回保存（まだ既存ファイルなし → バックアップは作られない）
            StudentCsvService.save(List.of(new Student("A", 10)), csv.toString());
            assertTrue(Files.exists(csv));

            // 6回「上書き保存」してバックアップを増やす（KEEP_BACKUPS=5を検証）
            for (int i = 0; i < 6; i++) {
                StudentCsvService.save(List.of(new Student("A", 10 + i)), csv.toString());
                // タイムスタンプ衝突防止（同秒だとバックアップ名が被る可能性）
                Thread.sleep(1100);
            }

            // バックアップファイルだけ数える
            List<Path> backups = Files.list(dir)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().startsWith("students_backup_"))
                    .filter(p -> p.getFileName().toString().endsWith(".csv"))
                    .sorted(Comparator.comparing(p -> p.getFileName().toString()))
                    .collect(Collectors.toList());

            // 最新5件だけ残っているはず
            assertEquals(5, backups.size());

            // 本体CSVも残っている
            assertTrue(Files.exists(csv));

        } finally {
            // 後始末（ディレクトリごと削除）
            Files.walk(dir)
                    .sorted(Comparator.reverseOrder())
                    .forEach(p -> {
                        try { Files.deleteIfExists(p); } catch (Exception ignored) {}
                    });
        }
    }
}