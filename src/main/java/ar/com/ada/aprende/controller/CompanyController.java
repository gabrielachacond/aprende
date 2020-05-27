package ar.com.ada.aprende.controller;

import ar.com.ada.aprende.model.dto.CompanyDTO;
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

    @PostMapping({"", "/"}) // localhost:8080/Companies y localhost:8080/companies/
    public ResponseEntity addNewCompany(@Valid @RequestBody CompanyDTO companyDTO) throws URISyntaxException {
        CompanyDTO companyDTOSaved = companyServices.save(companyDTO);
        return ResponseEntity
                .created(new URI("/companies/" + companyDTOSaved.getId()))
                .body(companyDTOSaved);

    }

}

