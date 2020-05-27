package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Modifying
    @Query(value = "ALTER TABLE Company AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
