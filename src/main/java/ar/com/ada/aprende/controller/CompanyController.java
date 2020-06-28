package ar.com.ada.aprende.controller;

import ar.com.ada.aprende.model.dto.CompanyDTO;
import ar.com.ada.aprende.model.dto.CompanyRepresentativeDTO;
import ar.com.ada.aprende.model.dto.CourseDTO;
import ar.com.ada.aprende.service.CompanyRepresentativeServices;
import ar.com.ada.aprende.service.CompanyServices;
import ar.com.ada.aprende.service.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class CompanyController {
    @Autowired
    @Qualifier("companyServices")
    private CompanyServices companyServices;

    @Autowired
    @Qualifier("companyRepresentativeServices")
    private CompanyRepresentativeServices companyRepresentativeServices;

    @Autowired
    @Qualifier("courseServices")
    private CourseServices courseServices;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/companies", "/companies/"}) // localhost:8080/Companies y localhost:8080/companies/
    public ResponseEntity addNewCompany(@Valid @RequestBody CompanyDTO companyDTO) throws URISyntaxException {
        CompanyDTO companyDTOSaved = companyServices.save(companyDTO);
        return ResponseEntity
                .created(new URI("/companies/" + companyDTOSaved.getId()))
                .body(companyDTOSaved);

    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/representatives", "/representatives/"})
    // localhost:8080/companies/representatives y localhost:8080/companies/representatives/
    public ResponseEntity addNewRepresentative(@Valid @RequestBody CompanyRepresentativeDTO companyRepresentativeDTO) throws URISyntaxException {
        CompanyRepresentativeDTO companyRepresentativeDTOSaved = companyRepresentativeServices.save(companyRepresentativeDTO);
        return ResponseEntity
                .created(new URI("/representatives/" + companyRepresentativeDTOSaved.getId()))
                .body(companyRepresentativeDTOSaved);

    }

    @PreAuthorize("hasRole('REPRESENTATIVE')")
    @PostMapping({"/courses", "/courses/"}) // localhost:8080/courses/
    public ResponseEntity addNewCourse(@Valid @RequestBody CourseDTO courseDTO) throws URISyntaxException {
        CourseDTO courseDTOSaved = courseServices.save(courseDTO);
        return ResponseEntity
                .created(new URI("/courses/" + courseDTOSaved.getId()))
                .body(courseDTOSaved);
    }


}

