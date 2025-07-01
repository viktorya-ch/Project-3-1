package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@OpenAPIDefinition
public class SchoolApplicationTests {


    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testCreateFaculty(){
        Faculty faculty = new Faculty(3,"John William", "Blue");
        faculty.setName("Potions");
        faculty.setColor("Blue");

        ResponseEntity<Faculty>response = restTemplate.postForEntity("faculties", faculty, Faculty.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Potions", response.getBody().getName());
    }

    @Test
    public void testEditFaculty() {
        Faculty editFaculty = new Faculty(3, "John William", "Blue");
        editFaculty.setName("Numerology");
        editFaculty.setColor("Green");

        restTemplate.put("faculties1", editFaculty);

        ResponseEntity<Faculty> response = restTemplate.getForEntity("faculties1", Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Numerology", response.getBody().getName());
    }

    @Test
    public void testDeleteFaculty() {
        restTemplate.delete("faculties1");

        ResponseEntity<Faculty> response = restTemplate.getForEntity("faculties1", Faculty.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testFindFacultiesByColor(){
        ResponseEntity<List>response = restTemplate.getForEntity("faculties? color=Blue", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size()>0);
    }

    @Test
    public void testSearchFaculties() {
        ResponseEntity<List> response = restTemplate.getForEntity("faculties/search?searchTerm = Potions", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testGetStudentsByFaculty() {
        ResponseEntity<List> response = restTemplate.getForEntity("faculties1/students", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    @Test
    public void testCreateStudent() {
        Student student = new Student(1, "Dean Ball", 20);
        student.setName("Dean Ball");
        student.getAge();

        ResponseEntity<Student> response = restTemplate.postForEntity("/student", student, Student.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Dean Ball", response.getBody().getName());
    }

    @Test
    public void testEditStudent() {
        Student editStudent = new Student(2, "Jell Ricky", 21);
        editStudent.setName("Jell Ricky");
        editStudent.getAge();

        restTemplate.put("/students/1", editStudent);

        ResponseEntity<Student> response = restTemplate.getForEntity("/students/1", Student.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jell Ricky", response.getBody().getName());
    }

    @Test
    public void testDeleteStudent() {
        restTemplate.delete("/students/1");

        ResponseEntity<Student> response = restTemplate.getForEntity("/students/1", Student.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testGetAllStudents() {
        ResponseEntity<Collection> response = restTemplate.getForEntity("/students", Collection.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testGetStudentsByAge() {
        ResponseEntity<List> response = restTemplate.getForEntity("/students/age?min=18&max=25", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() > 0);
    }


}
