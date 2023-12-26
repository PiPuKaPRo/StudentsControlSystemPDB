package edu.vsu.cs.sakovea.repository;

import edu.vsu.cs.sakovea.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findByFaculty_Id(Long faculty_Id);

    List<Profile> findByDepartment_Id(Long department_id);
}
