package ar.com.ada.aprende.model.mapper;

import ar.com.ada.aprende.model.dto.CompanyDTO;
import ar.com.ada.aprende.model.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper extends DataCycleMapper<CompanyDTO, Company> {


    CompanyMapper MAPPER = Mappers.getMapper(CompanyMapper.class);
}


