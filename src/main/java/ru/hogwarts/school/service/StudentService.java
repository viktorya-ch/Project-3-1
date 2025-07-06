package ru.hogwarts.school.service;


import org.apache.logging.log4j.message.Message;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class StudentService implements JavaStudentService {

    private static String STUDENT_SERVICE_URL = "https://api.hogwartsmap.org/age/19/student?q";

    @Autowired
    private static RestTemplate restTemplate;

    public static Student getStudent(String city) {
        return restTemplate.exchange(STUDENT_SERVICE_URL, HttpMethod.GET, new HttpEntity<>(HttpHeaders.EMPTY), Student.class).getBody();
    }

    Logger logger = (Logger) LoggerFactory.getLogger(StudentService.class);


    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Student createStudent(Student student) {
        logger.info(" Был вызван метод создания студента");
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            logger.warning(" Произошла ошибка во время создания студента " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Student> findStudent(Long id) {
        logger.info(" Был вызван метод добавления студента с id " + id);
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            logger.info(" Студент добавлен : " + student.get());
        } else {
            logger.warning(" Нет студента с id: " + id);
        }
        return student;
    }

    @Override
    public Student editStudent(Student student) {
        logger.info(" Был вызван метод  изменения студента с id " + student.getId());
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            logger.warning(" Произошла ошибка изменения студента: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteStudent(long id) {
        logger.info(" Был вызван метод удаления студента с id: " + id);
        Optional<Student>student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            logger.info(" Студент с id " + id + " был удален ");
            return true;
        }else {
            logger.warning(" Невозможно  удалить студента с id: " + id);
          return false;
        }
    }


    @Override
    public Collection<Student> getAllStudents() {
        logger.info(" Был вызван метод получения всех студентов ");
        try {
            Collection<Student> students = studentRepository.findAll();
            logger.info(" Всего  найдено студентов: " + students.size());
            return students;
        }catch(Exception e){
            logger.warning(" Ошибка получения всех студентов " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Student> getStudentsByAge(int min, int max) {
        logger.info(" Был вызван метод получения студентов по возрасту: " + min+ " и " + max);
        try {
            List<Student> students = studentRepository.findByAgeBetween(min, max);
            logger.info(" Всего  найдено студентов: " + students.size());
            return students;
        }catch(Exception e){
            logger.warning(" Ошибка получения студентов по  возрасту " + e.getMessage());
            throw e;
        }

    }


    @Override
    public Faculty getFacultyByStudent(Long studentId) {
        logger.info(" Был вызван метод получения факультета по студентам: " + studentId);
        try {
            Faculty faculty = (Faculty) studentRepository.findById(studentId).map(Student::getFaculty).orElse(null);
            if (faculty==null) {
                logger.warning(" На факультете нет студентов с id : " + studentId);
            }else {
                logger.info(" Найден студент по факультету " + studentId + " : " + faculty);
            } return faculty;
        }catch(Exception e){
            logger.warning(" Ошибка получения факультета по студенту " +studentId+ " : " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Student editStudent(Long id, String name, int age) {
        return null;
    }

    public List<String> getStudentsStartingWithA(){
        return studentRepository.findAll().stream().map(Student::getName).filter(name->name.toUpperCase().startsWith(" A ")).map(String::toUpperCase).sorted().collect(Collectors.toList());
    }

    public double getAverageAge(){
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()){
            return 0.0;
        }
        return students.stream().mapToInt(Student::getAge).average().orElse(0.0);
    }

}
