package edu.vsu.cs.sakovea.service;

import edu.vsu.cs.sakovea.dto.ProfileDTO;
import edu.vsu.cs.sakovea.model.Profile;
import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.model.Student;
import edu.vsu.cs.sakovea.dto.StudentDTO;
import edu.vsu.cs.sakovea.repository.ProfileRepository;
import edu.vsu.cs.sakovea.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public void addStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setStudentid_num(studentDTO.getStudentid_num());
        student.setCourse(studentDTO.getCourse());
        student.setGroup_num(studentDTO.getGroup());

        Optional<Profile> profile = profileRepository.findById((long) studentDTO.getProfileId());
        student.setProfile(profile.get());

        studentRepository.save(student);
    }

    public Student getStudentById(Long id) throws ChangeSetPersister.NotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void updateStudent(Long id, StudentDTO studentDTO) throws ChangeSetPersister.NotFoundException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(studentDTO.getName());
            student.setSurname(studentDTO.getSurname());
            student.setStudentid_num(studentDTO.getStudentid_num());
            student.setCourse(studentDTO.getCourse());
            student.setGroup_num(studentDTO.getGroup());

            Optional<Profile> profile = profileRepository.findById((long) studentDTO.getProfileId());
            student.setProfile(profile.get());

            studentRepository.save(student);
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }

    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

}