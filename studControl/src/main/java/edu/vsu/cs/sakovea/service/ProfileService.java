package edu.vsu.cs.sakovea.service;

import edu.vsu.cs.sakovea.dto.FacultyDTO;
import edu.vsu.cs.sakovea.model.Profile;
import edu.vsu.cs.sakovea.model.Faculty;
import edu.vsu.cs.sakovea.dto.ProfileDTO;
import edu.vsu.cs.sakovea.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;


    public void addProfile(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setName(profileDTO.getName());
        profile.setDepartment(profileDTO.getDepartment());
        profile.setFaculty(profileDTO.getFaculty());
        profileRepository.save(profile);
    }

    public Profile getProfileById(Long id) throws ChangeSetPersister.NotFoundException {
        return profileRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void updateProfile(Long id, ProfileDTO profileDTO) throws ChangeSetPersister.NotFoundException {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.setName(profileDTO.getName());
            profileRepository.save(profile);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    public List<Profile> getAllProfile() {
        return profileRepository.findAll();
    }
}