package ar.com.ada.aprende.model.repository.security;

import ar.com.ada.aprende.model.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "DELETE FROM user_has_authority", nativeQuery = true)
    void deletaAllUserHasAuthority();

    @Modifying
    @Query(value = "ALTER TABLE User AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();

    Optional<User> findByUsername(String username);

}
