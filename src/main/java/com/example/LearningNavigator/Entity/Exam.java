package com.example.LearningNavigator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "EXAM")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam_id", unique = true)
    private String examId;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "exam_subject",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "exam_student",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonManagedReference
    private List<Student> registeredStudents;

    public void addSubjectToExam(Subject subject) {
        if (subjects == null) {
            subjects = new ArrayList<>();
        }
        subjects.add(subject);
        if (subject.getExams() == null) {
            subject.setExams(new ArrayList<>());
        }
        subject.getExams().add(this);
    }
}
