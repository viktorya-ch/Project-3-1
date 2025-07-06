package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JavaStudentService {

    Student createStudent(Student student);

    Optional<Student> findStudent(Long id);

    Student editStudent(Student student);

    boolean deleteStudent(long id);

    Collection<Student> getAllStudents();

    List<Student> getStudentsByAge(int min, int max);

    Faculty getFacultyByStudent(Long studentId);


    Student editStudent(Long id, String name, int age);
}
