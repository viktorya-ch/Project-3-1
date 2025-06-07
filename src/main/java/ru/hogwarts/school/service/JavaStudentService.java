package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface JavaStudentService {

    Student createStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(long id);

    Collection<Student> getAllStudents();
}
