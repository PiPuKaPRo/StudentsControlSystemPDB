package edu.vsu.cs.sakovea.controller;

import edu.vsu.cs.sakovea.dto.FacultyDTO;
import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.service.FacultyService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/faculties")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/addFaculty")
    public String newFaculty(@ModelAttribute("faculty") Faculty faculty){
        return "/addFaculty";
    }

    @PostMapping
    public String addFaculty(@ModelAttribute("faculty") @Valid @RequestBody FacultyDTO facultyDTO,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/addFaculty";
        validateInput(facultyDTO.getName());
        facultyService.addFaculty(facultyDTO);
        return "redirect:/faculties";
    }

@SneakyThrows
@GetMapping("/{id}")
    public Faculty getFacultyById(@PathVariable Long id) {
        return facultyService.getFacultyById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping
    public String getAllFaculties(Model model) {
        model.addAttribute("faculties",facultyService.getAllFaculties());
        return "/faculties";
    }
    @SneakyThrows
    @GetMapping("/{id}/profiles")
    public String getFacultyProfiles(@PathVariable Long id, Model model){
        model.addAttribute("profiles", facultyService.getFacultyById(id).getProfiles());
        return "/profiles";
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
