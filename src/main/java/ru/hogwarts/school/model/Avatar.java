package ru.hogwarts.school.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;


    private String filePath;
    private long fileSize;

    private String mediaType;

    @Lob
    private byte[] data;

    @OneToOne (mappedBy = "avatar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Student student;


    public Avatar (Long id, String filePath, long fileSize, String mediaType, byte[] data, Student student){
        this.id=id;
        this.filePath=filePath;
        this.fileSize=fileSize;
        this.mediaType=mediaType;
        this.data=data;
        this.student=student;
    }

    public Avatar() {

    }

    public Long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public Student getStudent() {
        return student;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return fileSize == avatar.fileSize && Objects.equals(id, avatar.id) && Objects.equals(filePath, avatar.filePath) && Objects.equals(mediaType, avatar.mediaType) && Objects.deepEquals(data, avatar.data) && Objects.equals(student, avatar.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileSize, mediaType, Arrays.hashCode(data), student);
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", student=" + student +
                '}';
    }
}
