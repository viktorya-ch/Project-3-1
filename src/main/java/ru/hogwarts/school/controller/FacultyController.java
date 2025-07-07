package ru.hogwarts.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Optional<Faculty> getFaculty(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return faculty;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@PathVariable Long id, @RequestBody Faculty editFaculty) {
        Faculty faculty = facultyService.editFaculty(id,editFaculty.getName(),editFaculty.getColor());
        if (faculty == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return faculty;
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable Long id) {
        if(!facultyService.deleteFaculty(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Faculty> findFaculties(@RequestParam (required = false)String color){
        if (color !=  null && !color.isBlank()){
            return facultyService.findFaculty(color);
        }
        return Collections.emptyList();
    }

    @GetMapping ("/faculties/search")
    public List<Faculty> searchFaculties(@RequestParam String searchTerm){
        return facultyService.findByNameOrColorIgnoreCase(searchTerm);
    }

    @GetMapping ("/{facultyId}/students")
    public List<Student> getStudentByFaculty(@PathVariable Long facultyId) {
        return facultyService.getStudentsByFaculty(facultyId);
    }

    @GetMapping("/faculties/longest-name")
    public String getLongestFacultyName(){
        return facultyService.getLongestFacultyName();
    }

    @GetMapping("/faculties/sum")
    public int getSumOfFirstMillion(){
        return facultyService.getSumOfFirstMillion();
}


}