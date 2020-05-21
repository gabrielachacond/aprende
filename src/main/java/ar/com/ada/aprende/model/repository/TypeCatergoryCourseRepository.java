package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("typeCatergoryCourseRepository")
public interface TypeCatergoryCourseRepository extends JpaRepository<Company, Long> {
}
