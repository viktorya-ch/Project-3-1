package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface JavaStudentService {

    Student addStudent(Student student);

    Student getStudent(Long id);

    Student editStudent(Student student);

    Student deleteStudent(long id);

    Collection<Student> getAllStudents();
}
