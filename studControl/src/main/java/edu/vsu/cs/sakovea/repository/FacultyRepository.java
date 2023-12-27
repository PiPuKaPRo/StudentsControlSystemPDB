package edu.vsu.cs.sakovea.repository;

import edu.vsu.cs.sakovea.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}