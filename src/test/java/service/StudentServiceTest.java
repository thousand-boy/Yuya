package service;

import model.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceTest {

    @Test
    void sum_shouldReturnTotalScore() {
        List<Student> students = List.of(
                new Student("Alice", 100),
                new Student("Bob", 50),
                new Student("Aki", 80)
        );

        int actual = StudentService.sum(students);

        assertEquals(230, actual);
    }
}
