package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.TypeModalityCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("typeModalityCourseRepository")
public interface TypeModalityCourseRepository extends JpaRepository<TypeModalityCourse, Long> {

    @Modifying
    @Query(value = "ALTER TABLE TypeModalityCourse AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
