package ar.com.ada.aprende.model.mapper;

import ar.com.ada.aprende.model.dto.SocioEconomyStudyDTO;
import ar.com.ada.aprende.model.entity.SocioEconomyStudy;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SocioEconomyStudyMapper extends DataCycleMapper<SocioEconomyStudyDTO, SocioEconomyStudy> {

    SocioEconomyStudyMapper MAPPER = Mappers.getMapper(SocioEconomyStudyMapper.class);

    @InheritInverseConfiguration
    @Mapping(target = "participantId", ignore = true)
    SocioEconomyStudyDTO toDto(SocioEconomyStudy entity, @Context CycleAvoidingMappingContext context);
}
