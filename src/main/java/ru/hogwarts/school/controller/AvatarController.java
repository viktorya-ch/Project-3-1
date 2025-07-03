package ru.hogwarts.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @PostMapping
    public ResponseEntity<Avatar>uploadAvatar (@RequestBody Long studentId, @RequestParam ("file")MultipartFile file) {
        try {
            Avatar createdAvatar = avatarService.saveAvatar(file, studentId);
            return ResponseEntity.ok(createdAvatar);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getAvatarFromBd (@PathVariable Long id){
            byte[] avatarData = avatarService.getAvatarData(id);
            if (avatarData != null) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(avatarData);
            }else {
                return ResponseEntity.notFound().build();
            }

        }

        @GetMapping ("/file/{filename:.+}")
        public ResponseEntity<byte[]> getAvatarFromFile(@PathVariable String filename){
        try {
            File file = new File(" path/to/avatars/folder" + filename);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"" + filename + " \"").contentType(MediaType.APPLICATION_OCTET_STREAM).body(fileContent);
        }catch (IOException e){
            return ResponseEntity.notFound().build();
        }
             }


    @DeleteMapping
    public ResponseEntity<Void> deleteAvatar (@PathVariable Long id)
    {
        avatarService.deleteAvatar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public Page<Avatar>getAvatars
            ( @RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size){
        return avatarService.getAvatars(page,size);
    }


}
