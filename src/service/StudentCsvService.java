package service;

import model.Student;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption; // ★これが必要
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Stream;

public class StudentCsvService {
    private static final String BACKUP_PREFIX = "students_backup_";
    private static final String BACKUP_SUFFIX = ".csv";
    private static final int KEEP_BACKUPS = 5; // 最新5件だけ残す

    public static void save(List<Student> students, String filePath) {
        List<String> lines = new ArrayList<>();
        lines.add("name,score");

        for (Student s : students) {
            lines.add(s.getName() + "," + s.getScore());
        }

        try {
            Path path = Path.of(filePath);

            // 親フォルダが無ければ作る（data/）
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            // ★バックアップ：既存CSVがあるときだけ「日時付き」で保存
            if (Files.exists(path)) {
                String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

                Path parent = path.getParent();
                Path backupPath;

                if (parent != null) {
                    backupPath = parent.resolve("students_backup_" + ts + ".csv");
                } else {
                    backupPath = Path.of("students_backup_" + ts + ".csv");
                }

                Files.copy(path, backupPath, StandardCopyOption.COPY_ATTRIBUTES);
                System.out.println("バックアップ作成: " + backupPath);

                // ★追加：古いバックアップを削除（最新KEEP_BACKUPS件だけ残す）
                pruneOldBackups(parent != null ? parent : Path.of("."), KEEP_BACKUPS);
            }

            // 本保存
            Files.write(path, lines, StandardCharsets.UTF_8);
            System.out.println("保存しました: " + filePath);

        } catch (IOException e) {
            System.out.println("保存に失敗しました: " + e.getMessage());
        }
    }

    public static List<Student> load(String filePath) {
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            System.out.println("ファイルが見つかりません: " + filePath);
            return new ArrayList<>();
        }

        int loadedCount = 0;
        int skippedCount = 0;

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            List<Student> students = new ArrayList<>();

            for (String line : lines) {
                String trimmed = line.trim();

                // 空行スキップ
                if (trimmed.isEmpty()) {
                    skippedCount++;
                    continue;
                }

                // ヘッダー除外
                if (trimmed.equalsIgnoreCase("name,score")) {
                    continue;
                }

                String[] parts = trimmed.split(",", -1);
                if (parts.length != 2) {
                    skippedCount++;
                    continue;
                }

                String name = parts[0].trim();
                String scoreStr = parts[1].trim();

                if (name.isEmpty()) {
                    skippedCount++;
                    continue;
                }

                int score;
                try {
                    score = Integer.parseInt(scoreStr);
                } catch (NumberFormatException ex) {
                    skippedCount++;
                    continue;
                }

                // 範囲チェック（0〜100以外はスキップ）
                if (score < 0 || score > 100) {
                    skippedCount++;
                    continue;
                }

                students.add(new Student(name, score));
                loadedCount++;
            }

            System.out.println("読み込みました: " + filePath);
            System.out.println("読み込み成功: " + loadedCount + "件 / スキップ: " + skippedCount + "行");
            return students;

        } catch (IOException e) {
            System.out.println("読み込みに失敗しました: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    private static void pruneOldBackups(Path dir, int keepCount) {
        try (Stream<Path> stream = Files.list(dir)) {

            List<Path> backups = stream
                    .filter(Files::isRegularFile)
                    .filter(p -> {
                        String name = p.getFileName().toString();
                        return name.startsWith(BACKUP_PREFIX) && name.endsWith(BACKUP_SUFFIX);
                    })
                    // ファイル名が yyyyMMdd_HHmmss なので、名前順=日時順
                    .sorted(Comparator.comparing(p -> p.getFileName().toString()))
                    .toList();

            int deleteCount = backups.size() - keepCount;
            if (deleteCount <= 0) return;

            for (int i = 0; i < deleteCount; i++) {
                Files.deleteIfExists(backups.get(i));
            }

            System.out.println("古いバックアップを削除しました: " + deleteCount + "件");

        } catch (IOException e) {
            System.out.println("バックアップ整理に失敗しました: " + e.getMessage());
        }
    }
}