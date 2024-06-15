package com.example.LearningNavigator.Service;

import com.example.LearningNavigator.Entity.Student;
import com.example.LearningNavigator.Exception.ServiceException;
import com.example.LearningNavigator.Exception.UserNotFoundException;
import com.example.LearningNavigator.Repository.StudentRepository;
import com.example.LearningNavigator.Service.ServiceImp.StudentServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService = new StudentServiceImp();

    private List<Student> mockStudents;

    @BeforeEach
    public void setUp() {
        mockStudents = new ArrayList<>();
        mockStudents.add(new Student(1L, "S001", "Alice"));
        mockStudents.add(new Student(2L, "S002", "Bob"));
    }

    @Test
    public void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(mockStudents);

        List<Student> students = studentService.getAllStudent();

        assertEquals(2, students.size());
        assertEquals("Alice", students.get(0).getName());
        assertEquals("Bob", students.get(1).getName());

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void testGetStudentById() {
        Long studentId = 1L;
        when(studentRepository.findById(studentId.intValue())).thenReturn(Optional.of(mockStudents.get(0)));

        Student student = studentService.getStudent(studentId.intValue());

        assertEquals("Alice", student.getName());

        verify(studentRepository, times(1)).findById(studentId.intValue());
    }

    @Test
    public void testGetStudentByIdNotFound() {
        Long studentId = 3L;
        when(studentRepository.findById(studentId.intValue())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> studentService.getStudent(studentId.intValue()));

        verify(studentRepository, times(1)).findById(studentId.intValue());
    }

    @Test
    public void testGetAllStudentsServiceException() {
        when(studentRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        assertThrows(ServiceException.class, () -> studentService.getAllStudent());

        verify(studentRepository, times(1)).findAll();
    }

}
