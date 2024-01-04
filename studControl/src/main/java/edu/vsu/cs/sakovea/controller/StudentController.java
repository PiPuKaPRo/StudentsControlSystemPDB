package edu.vsu.cs.sakovea.controller;

import edu.vsu.cs.sakovea.model.Student;
import edu.vsu.cs.sakovea.dto.StudentDTO;
import edu.vsu.cs.sakovea.service.StudentService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping
    public void addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        validateInput(studentDTO.getName());
        studentService.addStudent(studentDTO);
    }

    @GetMapping("/signup")
    public String showSignUpForm(StudentDTO studentDTO) {
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String addUser(@Valid @RequestBody StudentDTO studentDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addStudent";
        }
        validateInput(studentDTO.getName());
        validateInput(studentDTO.getSurname());
        validateInput(String.valueOf(studentDTO.getStudentid_num()));
        validateInput(String.valueOf(studentDTO.getCourse()));
        validateInput(String.valueOf(studentDTO.getGroup()));
        validateInput(String.valueOf(studentDTO.getProfile()));
        studentService.addStudent(studentDTO);
        return "redirect:/students";
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @SneakyThrows
    @PutMapping("/{id}/updateStudent")
    public void updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        validateInput(studentDTO.getName());
        studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudent());
        return "/students";
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