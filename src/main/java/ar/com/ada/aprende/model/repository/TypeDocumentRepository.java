package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("typeDocumentRepository")
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {

}
