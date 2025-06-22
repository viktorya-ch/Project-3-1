package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
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
    public Optional<Student> findStudent(Long id){
        return studentRepository.findById(id);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean deleteStudent(long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            studentRepository.deleteById(id);
        }
        return deleteStudent(id);
    }



    @Override
    public Collection<Student>getAllStudents(){
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByAge(int min, int max) {
        return studentRepository.findByAgeBetween(min,max);
    }


    @Override
    public Faculty getFacultyByStudent(Long studentId) {
        return studentRepository.findById(studentId).map(Student::getFaculty).orElse(null);
    }

    @Override
    public Student getStudent(Long id) {
        return null;
    }

    @Override
    public Student editStudent(Long id, String name, int age) {
        return null;
    }
}
