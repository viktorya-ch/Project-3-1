package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JavaFacultyService {

    Faculty createFaculty(Faculty faculty);


    Faculty editFaculty(Faculty faculty);

    boolean deleteFaculty(long id);

    Collection<Faculty> getAllFaculties();


    List<Faculty> findByNameOrColorIgnoreCase(String searchTerm);

    List<Student>getStudentsByFaculty(Long facultyId);

    Optional<Faculty> getFaculty(Long id);

    Faculty editFaculty(Long id, String name, String color);
}
