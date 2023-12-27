package edu.vsu.cs.sakovea.controller;

import edu.vsu.cs.sakovea.dto.FacultyDTO;
import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.model.Student;
import edu.vsu.cs.sakovea.dto.StudentDTO;
import edu.vsu.cs.sakovea.service.ProfileService;
import edu.vsu.cs.sakovea.service.StudentService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping
    public void addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        validateInput(studentDTO.getName());
        studentService.addStudent(studentDTO);
    }

    @SneakyThrows
    @GetMapping("/students")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @SneakyThrows
    @PutMapping("/updateStudent")
    public void updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        validateInput(studentDTO.getName());
        studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/students")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudent();
    }

    private void validateInput(String input) {
        if (containsSqlInjection(input) || containsHtmlInjection(input)) {
            throw new RequestRejectedException("Invalid input");
        }
    }

    private boolean containsSqlInjection(String input) {
        String[] sqlKeywords = {"select", "insert", "update", "delete", "union", "drop", "truncate"};
        for (String keyword : sqlKeywords) {
            if (input.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsHtmlInjection(String input) {
        String[] htmlCharacters = {"<", ">", "&", "\"", "'", "/"};
        for (String character : htmlCharacters) {
            if (input.contains(character)) {
                return true;
            }
        }
        return false;
    }
}