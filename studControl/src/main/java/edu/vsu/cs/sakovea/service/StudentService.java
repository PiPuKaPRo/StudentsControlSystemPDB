package edu.vsu.cs.sakovea.service;

import edu.vsu.cs.sakovea.model.Direction;
import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.model.Student;
import edu.vsu.cs.sakovea.model.dto.StudentDTO;
import edu.vsu.cs.sakovea.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsByDirection(Long directionId) {
        return studentRepository.findByDirectionId(directionId);
    }

    public void addStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFullName(studentDTO.getFullName());
// Устанавливаем связи с факультетом и направлением
        Faculty faculty = new Faculty();
        faculty.setId(studentDTO.getFacultyId());
        student.setFaculty(faculty);

        Direction direction = new Direction();
        direction.setId(studentDTO.getDirectionId());
        student.setDirection(direction);

// Устанавливаем остальные свойства студента
        studentRepository.save(student);
    }
}