package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;

import static java.nio.file.Paths.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@OpenAPIDefinition
@WebMvcTest
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateStudent()throws Exception{
        String studentJson = "{\"name\":\"Jim Ball\",\"age\":20}";

        mockMvc.perform((RequestBuilder) post(" students").contentType(MediaType.APPLICATION_JSON).contentType(MediaType.valueOf(studentJson))).andExpect(status().isOk());
    }

    @Test
    public void testEditStudent()throws Exception{
        String editStudentJson = "{\"name\":\"Tony Smith\",\"age\":23}";
        mockMvc.perform((RequestBuilder) put(" students1").contentType(MediaType.APPLICATION_JSON).contentType(MediaType.valueOf(editStudentJson))).andExpect(status().isOk());
    }

    @Test
    public void testDeleteStudent()throws Exception{
        mockMvc.perform(delete("students1")).andExpect(status().isNoContent());

    }

    @Test
    public void testGetAllStudents()throws Exception{
        mockMvc.perform((RequestBuilder) get("students")).andExpect(status().isOk());

    }

    @Test
    public void testGetStudentsByAge()throws Exception{
        mockMvc.perform((RequestBuilder) get("students/age? min=18&max=25")).andExpect(status().isOk());
    }

    @Test
    public void testGetFacultyByStudent()throws Exception{
        mockMvc.perform((RequestBuilder) get("students1/faculty")).andExpect(status().isOk());
    }

    @Test
    public void testUploadAvatar()throws Exception{
        MockMultipartFile avatarFile = new MockMultipartFile("avatar", "avatar.png",MediaType.IMAGE_PNG_VALUE,"sample image content".getBytes());
        mockMvc.perform(multipart("students1/avatar").file(avatarFile)).andExpect(status().isOk());
    }



}
