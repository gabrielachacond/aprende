package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.Company;
import ar.com.ada.aprende.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Query(value = "ALTER TABLE Course AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
