package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService implements JavaFacultyService{

    @Autowired
    private FacultyRepository facultyRepository;


   @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty (Long id) {
        return facultyRepository.findById(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
       return facultyRepository.save(faculty);
    }

    @Override
    public void Faculty deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }


    @Override
    public Collection<Faculty>getAllFaculties(){
        return facultyRepository.findAll();
    }



}
