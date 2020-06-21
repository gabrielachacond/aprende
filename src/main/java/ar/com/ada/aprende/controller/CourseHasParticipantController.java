package ar.com.ada.aprende.controller;

import ar.com.ada.aprende.model.dto.CourseHasParticipantDTO;
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
}