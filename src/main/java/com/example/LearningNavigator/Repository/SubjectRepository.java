package com.example.LearningNavigator.Repository;

import com.example.LearningNavigator.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
