package ar.com.ada.aprende.model.mapper;

import ar.com.ada.aprende.model.dto.CourseHasParticipantDTO;
import ar.com.ada.aprende.model.entity.CourseHasParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseHasParticipantMapper extends DataCycleMapper<CourseHasParticipantDTO, CourseHasParticipant> {

    CourseHasParticipantMapper MAPPER = Mappers.getMapper(CourseHasParticipantMapper.class);
}
