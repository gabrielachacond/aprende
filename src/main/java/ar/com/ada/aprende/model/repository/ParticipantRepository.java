package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("participantRepository")
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
