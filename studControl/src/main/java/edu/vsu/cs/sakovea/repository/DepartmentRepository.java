package edu.vsu.cs.sakovea.repository;

import edu.vsu.cs.sakovea.model.Department;
import edu.vsu.cs.sakovea.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
