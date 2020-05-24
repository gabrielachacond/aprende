package ar.com.ada.aprende.service;

import ar.com.ada.aprende.component.BusinessLogicExceptionComponent;
import ar.com.ada.aprende.exception.BusinessLogicException;
import ar.com.ada.aprende.model.dto.CompanyDTO;
import ar.com.ada.aprende.model.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("CompanyServices")
public class CompanyServices implements Services<CompanyDTO> {

    @Autowired @Qualifier ("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier ("companyRepository")
    private CompanyRepository companyRepository;

   // de aca en adelante agregar company mapper aunque aun lo evaluo
}
