package com.example.LearningNavigator.Service;

import com.example.LearningNavigator.Entity.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentService {
     Student getStudent(int StudentId);
     List<Student> getAllStudent();
     String createStudentDetail( Student student);
     String updateStudent(Student student, int StudentId);
      String deleteStudent( int StudentId);
     void registerForExam(int studentId, int examId);
     void enrollStudentForSubject(int studentId, int subjectId);
}
