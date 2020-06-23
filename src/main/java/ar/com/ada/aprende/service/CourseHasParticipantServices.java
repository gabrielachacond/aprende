package ar.com.ada.aprende.service;

import ar.com.ada.aprende.component.BusinessLogicExceptionComponent;
import ar.com.ada.aprende.model.dto.CourseHasParticipantDTO;
import ar.com.ada.aprende.model.dto.CourseHisFinishDTO;
import ar.com.ada.aprende.model.dto.CourseScholarshipApprovalDTO;
import ar.com.ada.aprende.model.dto.StudentCourseApplicationDTO;
import ar.com.ada.aprende.model.entity.Course;
import ar.com.ada.aprende.model.entity.CourseHasParticipant;
import ar.com.ada.aprende.model.entity.CourseHasParticipantId;
import ar.com.ada.aprende.model.entity.Participant;
import ar.com.ada.aprende.model.mapper.CourseHasParticipantMapper;
import ar.com.ada.aprende.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.aprende.model.repository.CourseHasParticipantRepository;
import ar.com.ada.aprende.model.repository.CourseRepository;
import ar.com.ada.aprende.model.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("courseHasParticipantServices")
public class CourseHasParticipantServices {

    @Autowired
    @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    @Autowired
    @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired
    @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;

    @Autowired
    @Qualifier("courseHasParticipantRepository")
    private CourseHasParticipantRepository courseHasParticipantRepository;

    private CourseHasParticipantMapper courseHasParticipantMapper = CourseHasParticipantMapper.MAPPER;


    public CourseHasParticipantDTO saveCourseApplication(StudentCourseApplicationDTO dto, Long courseId, Long participantId) {
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Course", courseId));

        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Participant", participantId));

        CourseHasParticipantId id = new CourseHasParticipantId()
                .setCourseId(course.getId())
                .setParticipantId(participant.getId());

        courseHasParticipantRepository
                .findById(id)
                .ifPresent(courseHasParticipant -> {
                    throw logicExceptionComponent.getExceptionCourseApplicationAlreadyExists(id);
                });

        CourseHasParticipant courseHasParticipantToSave = new CourseHasParticipant()
                .setId(id)
                .setCourse(course)
                .setParticipant(participant);

        CourseHasParticipantDTO courseHasParticipantDTOSaved;
        if (dto.getIsScholaship()) {
            courseHasParticipantDTOSaved = this.saveCourseApplicationByScholaship(courseHasParticipantToSave);
        } else {
            courseHasParticipantDTOSaved = this.saveCourseApplicationByPurchase(courseHasParticipantToSave);
        }

        return courseHasParticipantDTOSaved;
    }

    private CourseHasParticipantDTO saveCourseApplicationByPurchase(CourseHasParticipant courseHasParticipantToSave) {
        Integer purchasedCouponCounter = courseHasParticipantToSave.getCourse().getPurchasedCouponCounter();
        if (purchasedCouponCounter == 0)
            throw logicExceptionComponent.getExceptionSoldOut(courseHasParticipantToSave.getCourse().getNameCourse());

        courseHasParticipantToSave.setIsScholaship(false);
        courseHasParticipantToSave.setIsApproved(true);
        courseHasParticipantToSave.setCourseHasFinish(false);
        courseHasParticipantToSave.setApprovalRate(0);
        courseHasParticipantToSave.getCourse().setPurchasedCouponCounter(purchasedCouponCounter - 1);

        CourseHasParticipant courseHasParticipantSave = courseHasParticipantRepository.save(courseHasParticipantToSave);

        CourseHasParticipantDTO courseHasParticipantDTOSaved = courseHasParticipantMapper.toDto(courseHasParticipantSave, context);

        return courseHasParticipantDTOSaved;

    }

    private CourseHasParticipantDTO saveCourseApplicationByScholaship(CourseHasParticipant courseHasParticipantToSave) {
        courseHasParticipantToSave.setIsScholaship(true);
        courseHasParticipantToSave.setCourseHasFinish(false);
        CourseHasParticipant courseHasParticipantSave = courseHasParticipantRepository.save(courseHasParticipantToSave);
        CourseHasParticipantDTO courseHasParticipantDTOSaved = courseHasParticipantMapper.toDto(courseHasParticipantSave, context);
        return courseHasParticipantDTOSaved;

    }

    public CourseHasParticipantDTO courseScholarshipApproval(CourseScholarshipApprovalDTO dto, Long courseId, Long participantId) {
        CourseHasParticipantId id = new CourseHasParticipantId()
                .setCourseId(courseId)
                .setParticipantId(participantId);
        CourseHasParticipant courseHasParticipant = courseHasParticipantRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("CourseHasParticipant", id));
        Integer scholarshipCouponCounter = courseHasParticipant.getCourse().getScholarshipCouponCounter();
        // esto verifica si se puede aprobar el curso
        if (scholarshipCouponCounter == 0)
            throw logicExceptionComponent.getExceptionSoldOut(courseHasParticipant.getCourse().getNameCourse());
        // si la aprobación es ok, se settea los datos y de actualiza el contador ScholarshipCouponCounter en el curso
        if (dto.getIsApproved()) {
            courseHasParticipant.setIsApproved(dto.getIsApproved());
            courseHasParticipant.setApprovalRate(dto.getApprovalRate());
            courseHasParticipant.getCourse().setScholarshipCouponCounter(scholarshipCouponCounter - 1);
        } else {
            //si no, solo se actualiza en false el estatus de aprobación y el porcentaje a 0
            courseHasParticipant.setIsApproved(dto.getIsApproved());
            courseHasParticipant.setApprovalRate(0);
        }
        CourseHasParticipantDTO courseHasParticipantDTOUpdated = courseHasParticipantMapper.toDto(courseHasParticipant, context);
        return courseHasParticipantDTOUpdated;
    }


    public CourseHasParticipantDTO courseHisFinish(CourseHisFinishDTO dto, Long courseId, Long participantId) {
        CourseHasParticipantId id = new CourseHasParticipantId()
                .setCourseId(courseId)
                .setParticipantId(participantId);
        CourseHasParticipant courseHasParticipant = courseHasParticipantRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("CourseHasParticipant", id));
        if (!courseHasParticipant.getCourseHasFinish() && dto.getCourseHasFinish())
            courseHasParticipant.setCourseHasFinish(dto.getCourseHasFinish());
        CourseHasParticipantDTO courseHasParticipantDTOUpdated = courseHasParticipantMapper.toDto(courseHasParticipant, context);
        return courseHasParticipantDTOUpdated;
    }


}