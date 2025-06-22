package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JavaFacultyService {

    Faculty createFaculty(Faculty faculty);

    Optional<Faculty> findFaculty (Long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);

    Collection<Faculty> getAllFaculties();


    List<Faculty> findByNameOrColorIgnoreCase(String searchTerm);
}
