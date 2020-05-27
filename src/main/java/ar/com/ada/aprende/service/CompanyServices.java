package ar.com.ada.aprende.service;

import ar.com.ada.aprende.component.BusinessLogicExceptionComponent;
import ar.com.ada.aprende.exception.BusinessLogicException;
import ar.com.ada.aprende.model.dto.CompanyDTO;
import ar.com.ada.aprende.model.entity.Company;
import ar.com.ada.aprende.model.entity.TypeCategoryCompany;
import ar.com.ada.aprende.model.mapper.CompanyMapper;
import ar.com.ada.aprende.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.aprende.model.repository.CompanyRepository;
import ar.com.ada.aprende.model.repository.TypeCategoryCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyServices")
public class CompanyServices implements Services<CompanyDTO> {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Autowired
    @Qualifier("typeCategoryCompanyRepository")
    private TypeCategoryCompanyRepository typeCategoryCompanyRepository;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;


    private CompanyMapper companyMapper = CompanyMapper.MAPPER;

    @Override
    public List<CompanyDTO> findAll() {
        return null;
    }

    @Override
    public CompanyDTO save(CompanyDTO dto) {
        Long typeCategoryCompanyId = dto.getTypeCategoryCompany().getId();
        TypeCategoryCompany typeCategoryCompany = typeCategoryCompanyRepository
                .findById(typeCategoryCompanyId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("TypeCategoryCompany", typeCategoryCompanyId));


        Company companyToSave = companyMapper.toEntity(dto, context);
        companyToSave.setTypeCategoryCompany(typeCategoryCompany);
        Company companySaved = companyRepository.save(companyToSave);
        CompanyDTO companyDTOSaved = companyMapper.toDto(companySaved, context);
        return companyDTOSaved;
    }

    @Override
    public void delete(Long id) {

    }

    // de aca en adelante agregar company mapper aunque aun lo evaluo
}
