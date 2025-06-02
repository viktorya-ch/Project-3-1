package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import java.util.*;

@Service
public class FacultyService implements JavaFacultyService{
    private Map<Long, Faculty>facultyMap = new HashMap<>();
    private long idCounter = 1L;

   @Override
    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(idCounter++);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty getFaculty (Long id) {
        return facultyMap.get(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        if (!facultyMap.containsKey(faculty.getId())) {
            return null;
        }
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty deleteFaculty(long id) {
        return facultyMap.remove(id);
    }


    @Override
    public Collection<Faculty>getAllFaculties(){
        return facultyMap.values();
    }

    public List<Faculty> findByColor(String color) {
        ArrayList<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultyMap.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }

}
