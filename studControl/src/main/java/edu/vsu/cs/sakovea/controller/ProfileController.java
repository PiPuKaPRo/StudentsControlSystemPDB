package edu.vsu.cs.sakovea.controller;


import edu.vsu.cs.sakovea.dto.FacultyDTO;
import edu.vsu.cs.sakovea.dto.ProfileDTO;
import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.model.Profile;
import edu.vsu.cs.sakovea.service.ProfileService;
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
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("profileDTO", new ProfileDTO());
        return "newProfile";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ProfileDTO profileDTO, Model model) {
        profileService.addProfile(profileDTO);
        return "redirect:/profiles";
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
    }

    @GetMapping
    public String getAllProfiles(Model model) {
        model.addAttribute("profiles",profileService.getAllProfile());
        return "/profiles";
    }

    @SneakyThrows
    @GetMapping("/{id}/students")
    public String getStudentsProfiles(@PathVariable Long id, Model model){
        model.addAttribute("students", profileService.getProfileById(id).getStudents());
        model.addAttribute("profile", profileService.getProfileById(id));
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
