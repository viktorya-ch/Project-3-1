package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface JavaFacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty (Long id);

    Faculty editFaculty(Faculty faculty);

    Faculty deleteFaculty(long id);

    Collection<Faculty> getAllFaculties();











}
