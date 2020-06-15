package ar.com.ada.aprende.controller;

import ar.com.ada.aprende.model.dto.CompanyDTO;
import ar.com.ada.aprende.model.dto.CompanyRepresentativeDTO;
import ar.com.ada.aprende.service.CompanyRepresentativeServices;
import ar.com.ada.aprende.service.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/companies") //siempre en plural
public class CompanyController {
    @Autowired
    @Qualifier("companyServices")
    private CompanyServices companyServices;

    @Autowired
    @Qualifier("companyRepresentativeServices")
    private CompanyRepresentativeServices companyRepresentativeServices;

    @PostMapping({"", "/"}) // localhost:8080/Companies y localhost:8080/companies/
    public ResponseEntity addNewCompany(@Valid @RequestBody CompanyDTO companyDTO) throws URISyntaxException {
        CompanyDTO companyDTOSaved = companyServices.save(companyDTO);
        return ResponseEntity
                .created(new URI("/companies/" + companyDTOSaved.getId()))
                .body(companyDTOSaved);

    }


    @PostMapping({"/representatives", "/representatives/"})
    // localhost:8080/companies/representatives y localhost:8080/companies/representatives/
    public ResponseEntity addNewRepresentative(@Valid @RequestBody CompanyRepresentativeDTO companyRepresentativeDTO) throws URISyntaxException {
        CompanyRepresentativeDTO companyRepresentativeDTOSaved = companyRepresentativeServices.save(companyRepresentativeDTO);
        return ResponseEntity
                .created(new URI("/representatives/" + companyRepresentativeDTOSaved.getId()))
                .body(companyRepresentativeDTOSaved);

    }
//todo agregar la logic de course aca

}

