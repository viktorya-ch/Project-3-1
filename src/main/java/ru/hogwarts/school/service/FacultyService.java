package ru.hogwarts.school.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.logging.Logger;

@Service
public class FacultyService implements JavaFacultyService{
    Logger logger = (Logger) LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    private ru.hogwarts.school.repository.FacultyRepository facultyRepository;


   @Override
    public Faculty createFaculty(Faculty faculty) {
       logger.info(" Был вызван метод создания факультета ");
       try {
           Faculty savedFaculty = facultyRepository.save(faculty);
           logger.info(" Создан факультет: " + savedFaculty);
           return savedFaculty;
       }catch (Exception e){
           logger.warning(" Ошибка создания факультета " + e.getMessage());
           throw e;
       }
    }

    @Override
    public Optional<Faculty> getFaculty (Long id) {
        logger.info(" Был вызван метод получения факультета по id: " + id);
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()){
        logger.info(" Получен факультет: " + faculty.get());
        }else {
            logger.warning(" Ошибка получения факультета по id: " + id);
        }
        return faculty;
    }


    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info(" Был вызван метод изменения факультета: " + faculty);
        try {
            return facultyRepository.save(faculty);
        }catch (Exception e){
            logger.warning(" Ошибка изменения факультета " + e.getMessage());
            throw e;
        }


    }

    @Override
    public boolean deleteFaculty(long id) {
        logger.info(" Был вызван метод удаления факультета по id: " + id);
        try {
            facultyRepository.deleteById(id);
            logger.info(" Удален факультет с id: " + id);
            return true;
        }catch (Exception e){
            logger.warning(" Ошибка удаления факультета с id: " + id + ":" + e.getMessage());
            return false;
        }
    }


    @Override
    public Collection<Faculty>getAllFaculties(){
        logger.info(" Был вызван метод получения всех факультетов ");
        try {
            Collection<Faculty> faculties = facultyRepository.findAll();
            logger.info(" Получены все факультеты: " + faculties.size());
            return faculties;
        }catch (Exception e){
            logger.warning(" Ошибка получения всех факультетов " + e.getMessage());
            throw e;
        }
    }


    public List<Faculty>findFaculty(String color){
        logger.info(" Был вызван метод получения факультета по цвету: " + color);
        try {
            return facultyRepository.findFaculty(color);
        }catch (Exception e){
            logger.warning(" Ошибка получения факультета по цвету " + color + ":" +e.getMessage());
            throw e;
        }

   }

    @Override
    public List<Faculty> findByNameOrColorIgnoreCase(String searchTerm) {
        logger.info(" Был вызван метод нахождения по имени или цвету игнорируя регистр: " + searchTerm);
        try {
            return facultyRepository.findByNameOrColorIgnoreCase(searchTerm);
        }catch (Exception e){
            logger.warning(" Ошибка нахождения по названию или цвету игнорируя регистр " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Student>getStudentsByFaculty(Long facultyId){
        logger.info(" Был вызван метод получения студентов по факультету " + facultyId);
        try {
            List<Student> students = facultyRepository.findById(facultyId).map(Faculty::getStudents).orElse(null);
            if (students==null){
            logger.info(" Получены студенты по факультету: " + facultyId);
        }else{
                logger.info("Cтуденты по факультету получены " + facultyId + ":" + students.size());
            }
            return students;
        }catch (Exception e){
            logger.warning(" Ошибка получения студентов по факультету: "+ facultyId + e.getMessage());
            throw e;
        }


    }
    @Override
    public Faculty editFaculty(Long id, String name, String color) {
        return null;
    }

}
