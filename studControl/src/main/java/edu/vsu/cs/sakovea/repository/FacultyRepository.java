package edu.vsu.cs.sakovea.repository;

import edu.vsu.cs.sakovea.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
// Дополнительные методы, если необходимо
}