package com.example.LearningNavigator.Repository;

import com.example.LearningNavigator.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
