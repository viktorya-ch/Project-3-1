package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping ("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id){
        Student student = studentService.getStudent(id);
        if (student == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return student;
    }

    @PostMapping
    @Operation(description = "{Добавление нового студента}",summary = "{Добавление нового студента}")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@PathVariable Long id, @RequestBody Student editStudent) {
        Student student = studentService.editStudent(id,editStudent.getName(),editStudent.getAge());
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return student;
    }
    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id){
        if(!studentService.deleteStudent(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public Collection<Student>getAllStudents(){
    return studentService.getAllStudents();
    }

    @GetMapping ("/students/age")
    public List<Student>getStudentsByAge(@RequestParam int min, @RequestParam int max){
        return studentService.getStudentsByAge(min,max);
    }

    @GetMapping ("/{studentId}/faculty")
    public Faculty getFacultyByStudent(@PathVariable Long studentId){
        return studentService.getFacultyByStudent(studentId);
    }



}
