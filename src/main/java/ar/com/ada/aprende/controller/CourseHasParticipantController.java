package ar.com.ada.aprende.controller;

import ar.com.ada.aprende.model.dto.CourseHasParticipantDTO;
import ar.com.ada.aprende.model.dto.CourseHisFinishDTO;
import ar.com.ada.aprende.model.dto.CourseScholarshipApprovalDTO;
import ar.com.ada.aprende.model.dto.StudentCourseApplicationDTO;
import ar.com.ada.aprende.service.CourseHasParticipantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CourseHasParticipantController {

    @Autowired
    @Qualifier("courseHasParticipantServices")
    private CourseHasParticipantServices courseHasParticipantServices;

    //  localhost:8080/courses/1/participants/1 y localhost:8080/courses/1/participants/1/
    @PostMapping({"/courses/{courseId}/partipants/{partipantId}", "/courses/{courseId}/partipants/{partipantId}/"})
    public ResponseEntity addNewStudentCourseApplication(
            @Valid @RequestBody StudentCourseApplicationDTO studentCourseApplicationDTO,
            @PathVariable Long courseId,
            @PathVariable Long partipantId
    ) {
        CourseHasParticipantDTO courseHasParticipantDTOSaved = courseHasParticipantServices
                .saveCourseApplication(studentCourseApplicationDTO, courseId, partipantId);
        return ResponseEntity.ok(courseHasParticipantDTOSaved);

    }

    //   http://localhost:8080/courses/1/partipants/1/approval
    @PostMapping({"/courses/{courseId}/partipants/{partipantId}/approval", "/courses/{courseId}/partipants/{partipantId}/approval/"})
    public ResponseEntity doCourseScholarshipApproval(
            @Valid @RequestBody CourseScholarshipApprovalDTO courseScholarshipApprovalDTO,
            @PathVariable Long courseId,
            @PathVariable Long partipantId
    ) {
        CourseHasParticipantDTO courseHasParticipantDTOUpdated = courseHasParticipantServices
                .courseScholarshipApproval(courseScholarshipApprovalDTO, courseId, partipantId);
        return ResponseEntity.ok(courseHasParticipantDTOUpdated);
    }

    //   http://localhost:8080/courses/1/partipants/1/finalize
    @PostMapping({"/courses/{courseId}/partipants/{partipantId}/finalize", "/courses/{courseId}/partipants/{partipantId}/finalize/"})
    public ResponseEntity doCourseHisFinishDTO(
            @Valid @RequestBody CourseHisFinishDTO courseHisFinishDTO,
            @PathVariable Long courseId,
            @PathVariable Long partipantId
    ) {
        CourseHasParticipantDTO courseHasParticipantDTOUpdated = courseHasParticipantServices.courseHisFinish (courseHisFinishDTO, courseId, partipantId);
        return ResponseEntity.ok(courseHasParticipantDTOUpdated);
    }
}
