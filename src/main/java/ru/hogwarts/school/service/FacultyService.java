package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService implements JavaFacultyService{

    @Autowired
    private ru.hogwarts.school.repository.FacultyRepository facultyRepository;


   @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Optional<Faculty> findFaculty (Long id) {
        return facultyRepository.findById(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
       return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
         facultyRepository.deleteById(id);   }


    @Override
    public Collection<Faculty>getAllFaculties(){
        return facultyRepository.findAll();
    }



    public List<Faculty>findFaculty(String color){
       return facultyRepository.findFaculty(color);
    }

    @Override
    public List<Faculty> findByNameOrColorIgnoreCase(String searchTerm) {
        return facultyRepository.findByNameOrColorIgnoreCase(searchTerm);
    }

    public List<Student>getStudentsByFaculty(Long facultyId){
       return facultyRepository.findById(facultyId).map(Faculty::getStudents).orElse(null);
    }

}
