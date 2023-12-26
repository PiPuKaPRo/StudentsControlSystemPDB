package edu.vsu.cs.sakovea.repository;

import edu.vsu.cs.sakovea.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFacultyId(Long facultyId);

    List<Student> findByDirectionId(Long directionId);

    List<Student> findByFacultyIdAndDirectionIdAndCourseAndGroup(
            Long facultyId, Long directionId, int course, String group
    );
}