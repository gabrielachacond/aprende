package ar.com.ada.aprende.controller;

import ar.com.ada.aprende.model.dto.ParticipantDTO;
import ar.com.ada.aprende.model.dto.SocioEconomyStudyDTO;
import ar.com.ada.aprende.service.ParticipantServices;
import ar.com.ada.aprende.service.SocioEconomyStudyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    @Qualifier("participantServices")
    private ParticipantServices participantServices;

    @Autowired
    @Qualifier("socioEconomyStudyServices")
    private SocioEconomyStudyServices socioEconomyStudyServices;


    @PostMapping({"", "/"}) // localhost:8080/participants y localhost:8080/participants/
    public ResponseEntity addNewParticipant(@Valid @RequestBody ParticipantDTO participantDTO) throws URISyntaxException {
        ParticipantDTO participantDTOSaved = participantServices.save(participantDTO);
        return ResponseEntity
                .created(new URI("/participants/" + participantDTOSaved.getId()))
                .body(participantDTOSaved);
    }

    @PreAuthorize("hasRole('PARTICIPANT')")
    @PostMapping({"/socioEconomyStudies", "/socioEconomyStudies/"}) // localhost:8080/socioEconomyStudies y localhost:8080/socioEconomyStudies/
    public ResponseEntity addNewSocioEconomyStudy(@Valid @RequestBody SocioEconomyStudyDTO socioEconomyStudyDTO) throws URISyntaxException {
        SocioEconomyStudyDTO socioEconomyStudyDTOSaved = socioEconomyStudyServices.save(socioEconomyStudyDTO);
        return ResponseEntity
                .created(new URI("/socioEconomyStudies/" + socioEconomyStudyDTOSaved.getId()))
                .body(socioEconomyStudyDTOSaved);
    }
}
