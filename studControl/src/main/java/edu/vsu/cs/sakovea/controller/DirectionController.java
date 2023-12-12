package edu.vsu.cs.sakovea.controller;

import edu.vsu.cs.sakovea.model.Direction;
import edu.vsu.cs.sakovea.model.dto.DirectionDTO;
import edu.vsu.cs.sakovea.service.DirectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/directions")
public class DirectionController {

    private final DirectionService directionService;

    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }
    @GetMapping("/{facultyId}")
    public String showDirections(@PathVariable Long facultyId, Model model) {
        List<Direction> directions = directionService.getDirectionsByFaculty(facultyId);
        model.addAttribute("directions", directions);
        model.addAttribute("directionDTO", new DirectionDTO());
        model.addAttribute("facultyId", facultyId);
        return "directions";
    }

    @PostMapping("/{facultyId}")
    public String addDirection(@ModelAttribute DirectionDTO directionDTO, @PathVariable Long facultyId) {
        directionService.addDirection(directionDTO, facultyId);
        return "redirect:/directions/{facultyId}";
    }
}
