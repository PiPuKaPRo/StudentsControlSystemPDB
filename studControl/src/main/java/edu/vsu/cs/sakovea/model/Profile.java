package edu.vsu.cs.sakovea.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty_id;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department_id;

    @OneToMany(mappedBy = "profile_id")
    private List<Student> students;
}
