package ar.com.ada.aprende.component;

import ar.com.ada.aprende.exception.ApiEntityError;
import ar.com.ada.aprende.exception.BusinessLogicException;
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
}

