package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;



@RestController
@RequestMapping ("/students")
public class StudentController {

    @Autowired
    private final StudentService studentService;
    private final AvatarService avatarService;


    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
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

    @PostMapping(value = "/{id}/avatar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String>uploadAvatar(@PathVariable long id, @RequestParam MultipartFile avatar) throws IOException{
        if (avatar.getSize() >= 1024 * 300){
            return ResponseEntity.badRequest().body(" Файл слишком большой ");
        }

        avatarService.uploadAvatar(id,avatar);
        return ResponseEntity.ok().build();
    }


    @GetMapping("count")
    public Long getTotalStudentCount(){
        return studentService.getTotalStudentCount();
    }
    @GetMapping("average-age")
    public Double getAverageStudentAge(){
        return studentService.getAverageStudentAge();
    }
    @GetMapping("last-five")
    public List<Student> getLastFiveStudents(){
        return studentService.getLastFiveStudents();
    }




}
