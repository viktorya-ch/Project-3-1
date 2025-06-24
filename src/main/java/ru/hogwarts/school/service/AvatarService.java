package ru.hogwarts.school.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {


    @Value("${path.to.avatars.folder}")
    private String avatarsDir;


    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository){
        this.studentService=studentService;
        this.avatarRepository=avatarRepository;
    }


    public void uploadAvatar (Long studentId, MultipartFile file) throws IOException{
        Student student = studentService.findStudent(studentId);

        Path filePath = Path.of(avatarsDir, studentId + " . " + getExtension(file));
        Files.createDirectories(filePath.getParent());

        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ) {
            bis.transferTo(bos);
        }


    }


}
