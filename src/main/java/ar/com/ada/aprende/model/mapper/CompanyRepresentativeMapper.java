package ar.com.ada.aprende.model.mapper;

import ar.com.ada.aprende.model.dto.CompanyRepresentativeDTO;
import ar.com.ada.aprende.model.entity.CompanyRepresentative;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CompanyRepresentativeMapper  extends DataCycleMapper<CompanyRepresentativeDTO, CompanyRepresentative>{

    CompanyRepresentativeMapper MAPPER = Mappers.getMapper(CompanyRepresentativeMapper.class);

    @InheritInverseConfiguration
    @Mapping(target = "companyId", ignore = true)
    CompanyRepresentativeDTO toDto(CompanyRepresentative entity, @Context CycleAvoidingMappingContext context);

}
