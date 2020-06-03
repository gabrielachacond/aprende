package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.CompanyRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("companyRepresentativeRepository")
public interface CompanyRepresentativeRepository extends JpaRepository<CompanyRepresentative, Long> {
    @Modifying
    @Query(value = "ALTER TABLE CompanyRepresentative AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
