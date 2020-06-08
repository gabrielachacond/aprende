package ar.com.ada.aprende.service;

import ar.com.ada.aprende.component.BusinessLogicExceptionComponent;
import ar.com.ada.aprende.model.dto.ParticipantDTO;
import ar.com.ada.aprende.model.entity.Participant;
import ar.com.ada.aprende.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.aprende.model.mapper.ParticipantMapper;
import ar.com.ada.aprende.model.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("participantServices")
public class ParticipantServices implements Services<ParticipantDTO> {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private ParticipantMapper participantMapper = ParticipantMapper.MAPPER;


    @Override
    public List<ParticipantDTO> findAll() {
        return null;
    }

    @Override
    public ParticipantDTO save(ParticipantDTO dto) {
        //todo:  agregar validacion por busqueda de documento a ver si el participante
        // exite, y si si existe agregar la exception

        Participant participantToSave = participantMapper.toEntity(dto, context);
        Participant participantSaved = participantRepository.save(participantToSave);
        ParticipantDTO participantDTOSaved = participantMapper.toDto(participantSaved, context);
        return participantDTOSaved;
    }

    @Override
    public void delete(Long id) {
    }
}
