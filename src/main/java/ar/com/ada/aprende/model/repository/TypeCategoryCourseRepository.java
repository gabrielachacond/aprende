package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.Company;
import ar.com.ada.aprende.model.entity.TypeCategoryCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("typeCategoryCourseRepository")
public interface TypeCategoryCourseRepository extends JpaRepository<TypeCategoryCourse, Long> {
}
