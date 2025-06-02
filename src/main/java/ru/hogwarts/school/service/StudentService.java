package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import java.util.*;

@Service
public class StudentService implements JavaStudentService{
    private Map<Long, Student> studentMap = new HashMap<>();
    private long idCounter = 1L;

    @Override
    public Student addStudent(Student student) {
        student.setId(idCounter++);
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public Student getStudent(Long id){
        return studentMap.get(id);
    }

    @Override
    public Student editStudent(Student student) {
        if (!studentMap.containsKey(student.getId())) {
            return null;
        }
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public Student deleteStudent(long id) {
        return studentMap.remove(id);
    }

    @Override
    public Collection<Student>getAllStudents(){
        return studentMap.values();
    }

    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }

}
