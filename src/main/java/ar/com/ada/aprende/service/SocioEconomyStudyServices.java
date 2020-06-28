package ar.com.ada.aprende.service;

import ar.com.ada.aprende.component.BusinessLogicExceptionComponent;
import ar.com.ada.aprende.model.dto.SocioEconomyStudyDTO;
import ar.com.ada.aprende.model.entity.Participant;
import ar.com.ada.aprende.model.entity.SocioEconomyStudy;
import ar.com.ada.aprende.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.aprende.model.mapper.SocioEconomyStudyMapper;
import ar.com.ada.aprende.model.repository.ParticipantRepository;
import ar.com.ada.aprende.model.repository.SocioEconomyStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("socioEconomyStudyServices")
public class SocioEconomyStudyServices implements Services<SocioEconomyStudyDTO> {
    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;

    @Autowired
    @Qualifier("socioEconomyStudyRepository")
    private SocioEconomyStudyRepository socioEconomyStudyRepository;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private SocioEconomyStudyMapper socioEconomyStudyMapper = SocioEconomyStudyMapper.MAPPER;


    @Override
    public List<SocioEconomyStudyDTO> findAll() {
        return null;
    }

    @Override
    public SocioEconomyStudyDTO save(SocioEconomyStudyDTO dto) {
        Long participantId = dto.getParticipantId();
        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Participant", participantId));

        SocioEconomyStudy socioEconomyStudyToSave = socioEconomyStudyMapper.toEntity(dto, context);
        socioEconomyStudyToSave.setParticipant(participant);

        SocioEconomyStudy socioEconomyStudySaved = socioEconomyStudyRepository.save(socioEconomyStudyToSave);
        SocioEconomyStudyDTO socioEconomyStudyDTOSaved = socioEconomyStudyMapper.toDto(socioEconomyStudySaved, context);

        return socioEconomyStudyDTOSaved;
    }

    @Override
    public void delete(Long id) {

    }
}
