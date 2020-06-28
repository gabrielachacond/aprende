package ar.com.ada.aprende.service;

import ar.com.ada.aprende.component.BusinessLogicExceptionComponent;
import ar.com.ada.aprende.model.dto.CompanyRepresentativeDTO;
import ar.com.ada.aprende.model.entity.Company;
import ar.com.ada.aprende.model.entity.CompanyRepresentative;
import ar.com.ada.aprende.model.entity.TypeDocument;
import ar.com.ada.aprende.model.mapper.CompanyRepresentativeMapper;
import ar.com.ada.aprende.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.aprende.model.repository.CompanyRepository;
import ar.com.ada.aprende.model.repository.CompanyRepresentativeRepository;
import ar.com.ada.aprende.model.repository.TypeDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyRepresentativeServices")
public class CompanyRepresentativeServices implements Services<CompanyRepresentativeDTO> {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("companyRepository")
    private CompanyRepository companyRepository;

    @Autowired
    @Qualifier("typeDocumentRepository")
    private TypeDocumentRepository typeDocumentRepository;

    @Autowired
    @Qualifier("companyRepresentativeRepository")
    private CompanyRepresentativeRepository companyRepresentativeRepository;


    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private CompanyRepresentativeMapper companyRepresentativeMapper = CompanyRepresentativeMapper.MAPPER;


    @Override
    public List<CompanyRepresentativeDTO> findAll() {
        return null;
    }

    @Override
    public CompanyRepresentativeDTO save(CompanyRepresentativeDTO dto) {
        Long companyId = dto.getCompanyId();
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Company", companyId));
        Long typeDocumentId = dto.getTypeDocument().getId();
        TypeDocument typeDocument = typeDocumentRepository
                .findById(typeDocumentId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("TypeDocument", typeDocumentId));

        CompanyRepresentative companyRepresentativeToSave = companyRepresentativeMapper.toEntity(dto, context);
        companyRepresentativeToSave.setTypeDocument(typeDocument);
        companyRepresentativeToSave.setCompany(company);
        CompanyRepresentative companyRepresentativeSaved = companyRepresentativeRepository.save(companyRepresentativeToSave);
        CompanyRepresentativeDTO companyRepresentativeDTOSaved = companyRepresentativeMapper.toDto(companyRepresentativeSaved, context);
        return companyRepresentativeDTOSaved;
    }

    @Override
    public void delete(Long id) {

    }
}
