package edu.vsu.cs.sakovea.repository;

import edu.vsu.cs.sakovea.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByProfileId(Long profile);

}