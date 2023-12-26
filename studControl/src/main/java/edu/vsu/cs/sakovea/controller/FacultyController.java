package edu.vsu.cs.sakovea.controller;

import edu.vsu.cs.sakovea.dto.FacultyDTO;
import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.service.FacultyService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/faculties")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @PostMapping
    public void addFaculty(@Valid @RequestBody FacultyDTO facultyDTO) {
        validateInput(facultyDTO.getName());
        facultyService.addFaculty(facultyDTO);
    }

@SneakyThrows
@GetMapping("/faculties")
    public Faculty getFacultyById(@PathVariable Long id) {
        return facultyService.getFacultyById(id);
    }

    @SneakyThrows
    @PutMapping("/faculties")
    public void updateFaculty(@PathVariable Long id, @Valid @RequestBody FacultyDTO facultyDTO) {
        validateInput(facultyDTO.getName());
        facultyService.updateFaculty(id, facultyDTO);
    }

    @DeleteMapping("/faculties")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
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
