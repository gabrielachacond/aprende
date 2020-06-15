package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.Company;
import ar.com.ada.aprende.model.entity.TypeModalityCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("typeModalityCourseRepository")
public interface TypeModalityCourseRepository extends JpaRepository<TypeModalityCourse, Long> {

    //todo hacer el metodo resetAutoincrementValue
}
