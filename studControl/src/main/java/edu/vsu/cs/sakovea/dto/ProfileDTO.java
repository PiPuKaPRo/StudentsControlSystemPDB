package edu.vsu.cs.sakovea.dto;

import edu.vsu.cs.sakovea.model.Department;
import edu.vsu.cs.sakovea.model.Faculty;
import lombok.Data;

@Data
public class ProfileDTO {
    private String name;
    private Faculty faculty;
    private Department department;
}
