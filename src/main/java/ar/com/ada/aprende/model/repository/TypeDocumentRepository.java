package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("typeDocumentRepository")
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
    @Modifying
    @Query(value = "ALTER TABLE Company AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
