package ru.hogwarts.school;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.hogwarts.school.controller.FacultyController;

import static java.nio.file.Paths.get;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@OpenAPIDefinition
@WebMvcTest
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateFaculty() throws Exception {
        String facultyAnna = "{name:Engineering,color:Blue}";

        mockMvc.perform((RequestBuilder) post("/faculties").contentType(MediaType.APPLICATION_JSON).contentType(MediaType.valueOf(facultyAnna))).andExpect(status().isOk());
    }

    @Test
    public void testEditFaculty() throws Exception {
        String editFacultyAnna = "{name:Arts,color:Red}";

        mockMvc.perform((RequestBuilder) put("/faculties/1").contentType(MediaType.APPLICATION_JSON).contentType(MediaType.valueOf(editFacultyAnna))).andExpect(status().isOk());
    }


    @Test
    public void testDeleteFaculty() throws Exception {
        mockMvc.perform((RequestBuilder) status().isNoContent());
    }

    @Test
    public void testFindFacultiesByColor() throws Exception {
        mockMvc.perform((RequestBuilder) get("/faculties? color=Blue"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$",hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[*].color").value("Blue"));
    }

    @Test
    public void testSearchFaculties() throws Exception {
        mockMvc.perform((RequestBuilder) get("/faculties/search? searchTerm=Engineering")).andExpect(status().isOk());
    }

    @Test
    public void testGetStudentsByFaculty() throws Exception {
        mockMvc.perform((RequestBuilder) get("/facultyes/1/students")).andExpect(status().isOk());
    }

}
