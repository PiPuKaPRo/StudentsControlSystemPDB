package edu.vsu.cs.sakovea.controller;

import edu.vsu.cs.sakovea.model.Student;
import edu.vsu.cs.sakovea.model.dto.StudentDTO;
import edu.vsu.cs.sakovea.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{directionId}")
    public String showStudents(@PathVariable Long directionId, Model model) {
        List<Student> students = studentService.getStudentsByDirection(directionId);
        model.addAttribute("students", students);
        model.addAttribute("studentDTO", new StudentDTO());
        model.addAttribute("directionId", directionId);
        return "students";
    }

    @PostMapping
    public String addStudent(@ModelAttribute StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
        return "redirect:/students";
    }
}