package ar.com.ada.aprende.controller;

import ar.com.ada.aprende.model.dto.CompanyDTO;
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

    @PostMapping({ "", "/" }) // localhost:8080/Companies y localhost:8080/companies/
   public ResponseEntity addNewCompany (@Valid @RequestBody CompanyDTO companyDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI("/companies/"+ companyDTO.getId())).body(companyDTO);
    }
}

