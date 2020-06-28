package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.TypeCategoryCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("typeCategoryCourseRepository")
public interface TypeCategoryCourseRepository extends JpaRepository<TypeCategoryCourse, Long> {

    @Modifying
    @Query(value = "ALTER TABLE TypeCategoryCourse AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
