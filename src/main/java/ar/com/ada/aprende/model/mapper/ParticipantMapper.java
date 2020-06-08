package ar.com.ada.aprende.model.mapper;

import ar.com.ada.aprende.model.dto.ParticipantDTO;
import ar.com.ada.aprende.model.entity.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipantMapper extends DataCycleMapper<ParticipantDTO, Participant> {

    ParticipantMapper MAPPER = Mappers.getMapper(ParticipantMapper.class);

}
