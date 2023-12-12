package edu.vsu.cs.sakovea.repository;

import edu.vsu.cs.sakovea.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    List<Direction> findByFacultyId(Long facultyId);
// Дополнительные методы, если необходимо
}
