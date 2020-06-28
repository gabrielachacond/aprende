package ar.com.ada.aprende.model.repository.security;

import ar.com.ada.aprende.model.entity.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("authorityRepository")
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Modifying
    @Query(value = "ALTER TABLE Role AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();
}
