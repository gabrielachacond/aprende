package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.Company;
import ar.com.ada.aprende.model.entity.CompanyRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository ("companyRepresentativeRepository")
public interface CompanyRepresentativeRepository extends JpaRepository<CompanyRepresentative, Long> {
}
