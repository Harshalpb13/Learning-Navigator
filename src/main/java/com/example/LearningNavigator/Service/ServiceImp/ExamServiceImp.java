package com.example.LearningNavigator.Service.ServiceImp;

import com.example.LearningNavigator.Entity.Exam;
import com.example.LearningNavigator.Entity.Student;
import com.example.LearningNavigator.Entity.Subject;
import com.example.LearningNavigator.Exception.ServiceException;
import com.example.LearningNavigator.Repository.ExamRepository;
import com.example.LearningNavigator.Repository.SubjectRepository;
import com.example.LearningNavigator.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamServiceImp implements ExamService {
    @Autowired
    ExamRepository examRepository;

    @Autowired
    SubjectRepository subjectRepository;
    @Override
    public List<Exam> getAllExams() {
        try {
            return examRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while retrieving all exams", e);
        }
    }

    @Override
    public Exam getExamById(int examId) {
        try {
            return examRepository.findById( examId)
                    .orElseThrow(() -> new IllegalArgumentException("Exam not found with ID: " + examId));
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while retrieving the exam", e);
        }
    }

    @Override
    public Exam createExam(Exam exam) {
        try {
            return examRepository.save(exam);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while creating the exam", e);
        }
    }

    @Override
    public Exam updateExam(int examId, Exam examDetails) {
        try {
            Exam exam = examRepository.findById(examId)
                    .orElseThrow(() -> new IllegalArgumentException("Exam not found with ID: " + examId));

            exam.setExamId(examDetails.getExamId());
            exam.setSubjects(examDetails.getSubjects());
            exam.setRegisteredStudents(examDetails.getRegisteredStudents());

            return examRepository.save(exam);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the exam", e);
        }
    }

    @Override
    public void deleteExam(int examId) {
        try {
            Exam exam = examRepository.findById(examId)
                    .orElseThrow(() -> new IllegalArgumentException("Exam not found with ID: " + examId));
            examRepository.delete(exam);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the exam", e);
        }
    }

    @Transactional
    public void registerSubjectForExam(int examId, int subjectId) {
        try {
            Exam exam = examRepository.findById(examId)
                    .orElseThrow(() -> new IllegalArgumentException("Exam not found with ID: " + examId));

            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + subjectId));

            exam.addSubjectToExam(subject);
            examRepository.save(exam);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while registering the subject for the exam", e);
        }
    }
}
