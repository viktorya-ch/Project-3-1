package ru.hogwarts.school.service;


import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    Logger logger = LoggerFactory.getLogger(AvatarService.class);

    @Autowired
    private AvatarRepository avatarRepository;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public Avatar saveAvatar(MultipartFile file, Long studentId) throws IOException {
        String filePath = avatarsDir + " / " + studentId + " _ " + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        Avatar avatar = new Avatar();
        avatar.setFilePath(filePath);
        avatar.setFileSize(file.getSize());

        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        return avatarRepository.save(avatar);
    }

    public Optional<Avatar> getAvatarById(Long id) {
        return avatarRepository.findById(id);
    }

    public byte[] getAvatarData(Long id) {
        return avatarRepository.findById(id).map(Avatar::getData).orElse(null);
    }

    public void deleteAvatar(Long id) {
        avatarRepository.deleteById(id);
    }


    private final StudentService studentService;


    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }


    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Optional<Student> student = studentService.findStudent(studentId);

        Path filePath = Path.of(avatarsDir, studentId + " . ");
        Files.createDirectories(filePath.getParent());

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }


    }


}
