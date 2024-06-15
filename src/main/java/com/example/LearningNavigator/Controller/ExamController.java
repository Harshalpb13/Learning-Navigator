package com.example.LearningNavigator.Controller;



import com.example.LearningNavigator.Entity.Exam;
import com.example.LearningNavigator.Entity.Student;
import com.example.LearningNavigator.Exception.ServiceException;
import com.example.LearningNavigator.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public ResponseEntity<?> getAllExams() {
        try {
            List<Exam> exams = examService.getAllExams();
            return ResponseEntity.ok(exams);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{examId}")
    public ResponseEntity<?> getExamById(@PathVariable int examId) {
        try {
            Exam exam = examService.getExamById(examId);
            return ResponseEntity.ok(exam);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createExam(@RequestBody Exam exam) {
        try {
            Exam createdExam = examService.createExam(exam);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExam);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{examId}")
    public ResponseEntity<?> updateExam(@PathVariable int examId, @RequestBody Exam examDetails) {
        try {
            Exam updatedExam = examService.updateExam(examId, examDetails);
            return ResponseEntity.ok(updatedExam);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<?> deleteExam(@PathVariable int examId) {
        try {
            examService.deleteExam(examId);
            return ResponseEntity.ok("Exam deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{examId}/subjects/{subjectId}")
    public ResponseEntity<?> registerSubjectForExam(@PathVariable int examId, @PathVariable int subjectId) {
        try {
            examService.registerSubjectForExam(examId, subjectId);
            return ResponseEntity.ok("Subject registered for exam successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
