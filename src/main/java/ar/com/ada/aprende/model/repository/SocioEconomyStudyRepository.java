package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.Company;
import ar.com.ada.aprende.model.entity.SocioEconomyStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("socioEconomyStudyRepository")
public interface SocioEconomyStudyRepository extends JpaRepository<SocioEconomyStudy, Long> {
}
