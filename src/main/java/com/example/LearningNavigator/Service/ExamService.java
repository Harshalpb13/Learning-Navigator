package com.example.LearningNavigator.Service;

import com.example.LearningNavigator.Entity.Exam;
import com.example.LearningNavigator.Entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ExamService {
    List<Exam> getAllExams();
    Exam getExamById(int examId);
    Exam createExam(Exam exam);
    Exam updateExam(int examId, Exam exam);
    void deleteExam(int examId);

    void registerSubjectForExam(int examId,int subjectId);
}
