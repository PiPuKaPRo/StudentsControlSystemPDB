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
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping
    public void addProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        validateInput(profileDTO.getName());
        profileService.addProfile(profileDTO);
    }

    @SneakyThrows
    @GetMapping("/profiles")
    public Profile getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }

    @SneakyThrows
    @PutMapping("/updateProfile")
    public void updateProfile(@PathVariable Long id, @Valid @RequestBody ProfileDTO profileDTO) {
        validateInput(profileDTO.getName());
        profileService.updateProfile(id, profileDTO);
    }

    @DeleteMapping("/profiles")
    public void deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfile();
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
