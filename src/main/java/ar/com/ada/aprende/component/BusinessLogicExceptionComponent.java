package ar.com.ada.aprende.component;

import ar.com.ada.aprende.exception.ApiEntityError;
import ar.com.ada.aprende.exception.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("businessLogicExceptionComponent")
public class BusinessLogicExceptionComponent {

    public void throwExceptionEntityNotFound(String entityName, Long id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "NotFound",
                "The " + entityName + " with id '" + id + "' does not exist"
        );

        throw new BusinessLogicException(
                entityName + " does not exist",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );
    }
}

