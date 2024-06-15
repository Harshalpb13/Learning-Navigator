package com.example.LearningNavigator.Service.ServiceImp;

import com.example.LearningNavigator.Entity.Subject;
import com.example.LearningNavigator.Exception.ServiceException;
import com.example.LearningNavigator.Repository.SubjectRepository;
import com.example.LearningNavigator.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImp implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject getSubject(int subjectId) {
        try {
            return subjectRepository.findById( subjectId)
                    .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + subjectId));
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while retrieving the subject", e);
        }
    }

    @Override
    public List<Subject> getAllSubject() {
        try {
            return subjectRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while retrieving all subjects", e);
        }
    }

    @Override
    public String createSubject(Subject subject) {
        try {
            subjectRepository.save(subject);
            return "Success";
        } catch (Exception e) {
            throw new ServiceException("An error occurred while creating the subject", e);
        }
    }

    @Override
    public String updateSubject(Subject subject, int subjectId) {
        try {
            Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

            if (subjectOptional.isPresent()) {
                Subject existingSubject = subjectOptional.get();
                existingSubject.setName(subject.getName());
                subjectRepository.save(existingSubject);
                return "Success";
            } else {
                throw new IllegalArgumentException("Subject not found with ID: " + subjectId);
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the subject", e);
        }
    }

    @Override
    public String deleteSubject(int subjectId) {
        try {
            subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + subjectId));
            subjectRepository.deleteById( subjectId);
            return "Success";
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the subject", e);
        }
    }
}
