package edu.vsu.cs.sakovea.service;

import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public void addFaculty(String name) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        facultyRepository.save(faculty);
    }
}