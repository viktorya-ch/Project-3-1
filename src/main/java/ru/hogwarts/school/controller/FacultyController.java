package ru.hogwarts.school.controller;


import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

//   @GetMapping
//    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color) {
//        if (color != null && !color.isBlank()) {
//            return ResponseEntity.ok((Collection<Faculty>) facultyService.findByColor(color));
//        }
//        return ResponseEntity.ok((Collection<Faculty>) Collections.emptyList());
//    }

//     @GetMapping
//    @ResponseBody
//    public Collection<Faculty>findFaculties(@RequestParam (required = false)String color){
//        if (color !=  null && !color.isBlank()){
//            return facultyService.findByColor(color);
//        }
//        return Collections.emptyList();
//
//     }

    @GetMapping
    public List<Faculty> findFaculties(@RequestParam (required = false)String color){
        if (color !=  null && !color.isBlank()){
            return facultyService.findByColor(color);
        }
        return Collections.emptyList();
    }


}