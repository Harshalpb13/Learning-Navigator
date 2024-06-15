package com.example.LearningNavigator.Entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SUBJECT")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String subjectId;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    private List<Exam> exams;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledSubjects")
    private List<Student> enrolledStudents;
}
