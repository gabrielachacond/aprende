package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.TypeCategoryCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("typeCategoryCompanyRepository")
public interface TypeCategoryCompanyRepository extends JpaRepository<TypeCategoryCompany, Long> {

    @Modifying
    @Query(value = "ALTER TABLE TypeCategoryCompany AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
