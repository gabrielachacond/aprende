package ar.com.ada.aprende.model.mapper;

import ar.com.ada.aprende.model.dto.CompanyDTO;
import ar.com.ada.aprende.model.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CompanyMapper  extends DataMapper<CompanyDTO, Company> {

        Company toEntity(CompanyDTO companyDTO);

        CompanyDTO toDto(Company entity);

        default Company fromId(Long id) {
            if (id == null) return null;
            return new Company(id);
        }
    }


