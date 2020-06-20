package ar.com.ada.aprende.model.repository;

import ar.com.ada.aprende.model.entity.CourseHasParticipant;
import ar.com.ada.aprende.model.entity.CourseHasParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("courseHasParticipantRepository")
public interface CourseHasParticipantRepository extends JpaRepository<CourseHasParticipant, CourseHasParticipantId> {
}
