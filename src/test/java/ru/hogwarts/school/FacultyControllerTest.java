package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@OpenAPIDefinition
public class FacultyControllerTest {

    @LocalServerPort
    private int port;


    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void testCreateFaculty(){
        Faculty faculty = new Faculty(3,"John William", "Blue");
        faculty.setName("Potions");
        faculty.setColor("Blue");

        ResponseEntity<Faculty> response = restTemplate.postForEntity("faculties", faculty, Faculty.class);

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









}
