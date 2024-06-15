package com.example.LearningNavigator.Service.ServiceImp;

import com.example.LearningNavigator.Entity.Exam;
import com.example.LearningNavigator.Entity.Student;
import com.example.LearningNavigator.Entity.Subject;
import com.example.LearningNavigator.Exception.ServiceException;
import com.example.LearningNavigator.Exception.UserNotFoundException;
import com.example.LearningNavigator.Repository.ExamRepository;
import com.example.LearningNavigator.Repository.StudentRepository;
import com.example.LearningNavigator.Repository.SubjectRepository;
import com.example.LearningNavigator.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Student getStudent(int studentId) {
        try {
            return studentRepository.findById((int) studentId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + studentId));
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while retrieving the student", e);
        }
    }

    @Override
    public List<Student> getAllStudent() {
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while retrieving all students", e);
        }
    }

    @Override
    public String createStudentDetail(Student student) {
        try {
            studentRepository.save(student);
            return "success";
        } catch (Exception e) {
            throw new ServiceException("An error occurred while creating student details", e);
        }
    }

    public String updateStudent(Student student, int studentId) {
        try {
            Optional<Student> studentOptional = studentRepository.findById((int) studentId);

            if (studentOptional.isPresent()) {
                Student existingStudent = studentOptional.get();
                existingStudent.setName(student.getName());
                studentRepository.save(existingStudent);
                return "Success";
            } else {
                throw new UserNotFoundException("Student not found with ID: " + studentId);
            }
        } catch (UserNotFoundException e) {
            return e.getMessage();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the student", e);
        }
    }

    public String deleteStudent(int studentId) {
        try {
            if (!studentRepository.existsById((int) studentId)) {
                throw new UserNotFoundException("Student not found with ID: " + studentId);
            }
            studentRepository.deleteById((int) studentId);
            return "Success";
        } catch (UserNotFoundException e) {
            return e.getMessage();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the student", e);
        }
    }

    @Transactional
    public void registerForExam(int studentId, int examId) {
        try {
            Student student = studentRepository.findById((int) studentId)
                    .orElseThrow(() -> new UserNotFoundException("Student not found with ID: " + studentId));
            Exam exam = examRepository.findById((int) examId)
                    .orElseThrow(() -> new IllegalArgumentException("Exam not found with ID: " + examId));

            // Check if the student is enrolled in all the subjects covered by the exam
            boolean isEnrolledInAllSubjects = exam.getSubjects().stream()
                    .allMatch(subject -> student.getEnrolledSubjects().contains(subject));

            if (!isEnrolledInAllSubjects) {
                throw new IllegalArgumentException("Student must be enrolled in all subjects covered by the exam to register for the exam");
            }

            student.registerForExam(exam);
            studentRepository.save(student);
        } catch (UserNotFoundException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while registering for the exam", e);
        }
    }

    public void enrollStudentForSubject(int studentId, int subjectId) {
        try {
            Student student = studentRepository.findById((int) studentId)
                    .orElseThrow(() -> new UserNotFoundException("Student not found with ID: " + studentId));
            Subject subject = subjectRepository.findById((int) subjectId)
                    .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + subjectId));

            student.addEnrolledSubject(subject);
            studentRepository.save(student);
        } catch (UserNotFoundException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while enrolling the student for the subject", e);
        }
    }
}
