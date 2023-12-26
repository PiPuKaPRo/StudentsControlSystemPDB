package edu.vsu.cs.sakovea.dto;

import edu.vsu.cs.sakovea.model.Profile;
import lombok.Data;

@Data
public class StudentDTO {
    private String name;
    private String surname;
    private int studentid_num;
    private Profile profile_id;
    private int course;
    private int group;
}
