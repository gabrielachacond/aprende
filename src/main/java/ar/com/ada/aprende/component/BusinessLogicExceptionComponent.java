package ar.com.ada.aprende.component;

import ar.com.ada.aprende.exception.ApiEntityError;
import ar.com.ada.aprende.exception.BusinessLogicException;
import ar.com.ada.aprende.model.entity.CourseHasParticipantId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("businessLogicExceptionComponent")
public class BusinessLogicExceptionComponent {

    public RuntimeException throwExceptionEntityNotFound(String entityName, Long id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "NotFound",
                "The " + entityName + " with id '" + id + "' does not exist"
        );

        return new BusinessLogicException(
                entityName + " does not exist",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );
    }

    public RuntimeException getExceptionSoldOut(String courseName) {
        ApiEntityError apiEntityError = new ApiEntityError(
                courseName,
                "NotAvailable",
                "There are no places available for the course " + courseName
        );

        return new BusinessLogicException(
                "no places available for the course",
                HttpStatus.BAD_REQUEST,
                apiEntityError
        );
    }

    public RuntimeException getExceptionCourseApplicationAlreadyExists(CourseHasParticipantId id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                "CourseApplication",
                "ApplicationAlreadyExists",
                "application already exists for course id " + id.getCourseId() + " and participan id " + id.getParticipantId()
        );
        return new BusinessLogicException(
                "this application already exists",
                HttpStatus.BAD_REQUEST,
                apiEntityError
        );
    }

    public RuntimeException throwExceptionEntityNotFound(String entityName, CourseHasParticipantId id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "NotFound",
                "The " + entityName + " with Course id '" + id.getCourseId() + "' and Participant id '" + id.getParticipantId() + "' does not exist"
        );
        return new BusinessLogicException(
                entityName + " does not exist",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );
    }

}

