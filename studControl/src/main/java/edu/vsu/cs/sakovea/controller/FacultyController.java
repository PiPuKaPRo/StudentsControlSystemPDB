package edu.vsu.cs.sakovea.controller;

import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.model.dto.FacultyDTO;
import edu.vsu.cs.sakovea.service.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String showFaculties(Model model) {
        List<Faculty> faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        model.addAttribute("facultyDTO", new FacultyDTO());
        return "faculties";
    }

    @PostMapping
    public String addFaculty(@RequestParam String name) {
        facultyService.addFaculty(name);
        return "redirect:/faculties";
    }
}
