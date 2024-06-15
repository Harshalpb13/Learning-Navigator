package com.example.LearningNavigator.Service;

import com.example.LearningNavigator.Entity.Subject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SubjectService {
    Subject getSubject( int SubjectId);
    List<Subject> getAllSubject();
    String createSubject( Subject subject);
     String updateSubject(Subject subject,int subjectId);
     String deleteSubject(int SubjectId);
}
