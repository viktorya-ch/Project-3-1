package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService implements JavaStudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id){
        return studentRepository.findById(id);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void Student deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student>getAllStudents(){
        return studentRepository.findAll();
    }



}
