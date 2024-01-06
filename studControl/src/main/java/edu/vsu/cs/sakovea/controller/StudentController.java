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

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("studentDTO", new StudentDTO());
        return "newStudent";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute StudentDTO studentDTO, Model model) {
        studentService.addStudent(studentDTO);
        return "redirect:/students";
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }


    @GetMapping("/update/{id}")
    public String update(Model model) {
        model.addAttribute("studentDTO", new StudentDTO());
        return "updateStudent";
    }

    @SneakyThrows
    @GetMapping("/update/{id}/updateStudent")
    public String update(@PathVariable Long id ,@ModelAttribute StudentDTO studentDTO, Model model) {
        studentService.updateStudent(id,studentDTO);
        return "redirect:/profiles/" + studentDTO.getProfileId() + "/students";
    }


    @SneakyThrows
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        studentService.deleteStudent(student.getId());
        return "redirect:/profiles/" + student.getProfile().getId() + "/students";
    }

    @GetMapping
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