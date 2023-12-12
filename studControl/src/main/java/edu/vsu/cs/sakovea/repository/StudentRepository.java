package edu.vsu.cs.sakovea.repository;

import edu.vsu.cs.sakovea.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByDirectionId(Long directionId);
// Дополнительные методы, если необходимо
}