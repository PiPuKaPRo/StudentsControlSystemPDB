package edu.vsu.cs.sakovea.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentWithIdDTO {
    private Long id;
    private String name;
    private String surname;
    private int studentid_num;
    private Long profileId;
    private int course;
    private int group;
}