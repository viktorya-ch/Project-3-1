package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    List<Faculty> findByColor(String color);

@Query("SELECT f FROM Faculty f WHERE LOWER(f.name)LIKE CONCAT('%', LOWER(:searchTerm),'%')" + "OR LOWER (f.color)LIKE CONCAT('%', LOWER(:searchTerm),'%')")
    List<Faculty>findByNameIgnoreCaseOrColorIgnoreCase(@Param("name")String name, @Param("color")  String color);
}
