package ar.com.ada.aprende.controller;

import ar.com.ada.aprende.model.dto.ParticipantDTO;
import ar.com.ada.aprende.service.CompanyServices;
import ar.com.ada.aprende.service.ParticipantServices;
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
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    @Qualifier("participantServices")
    private ParticipantServices participantServices;

    @PostMapping({"","/"}) // localhost:8080/participants y localhost:8080/participants/
    public ResponseEntity addNewParticipant (@Valid @RequestBody ParticipantDTO participantDTO) throws URISyntaxException{
        ParticipantDTO participantDTOSaved = participantServices.save(participantDTO);
        return  ResponseEntity
                .created(new URI("/participants/" + participantDTOSaved.getId()))
                .body(participantDTOSaved);
    }
}
