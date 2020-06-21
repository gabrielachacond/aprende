package ar.com.ada.aprende.service;

import ar.com.ada.aprende.component.BusinessLogicExceptionComponent;
import ar.com.ada.aprende.model.dto.CourseHasParticipantDTO;
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

}
