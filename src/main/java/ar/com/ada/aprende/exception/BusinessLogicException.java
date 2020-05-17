package ar.com.ada.aprende.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BusinessLogicException extends RuntimeException {

    private String defaultMessage;
    private HttpStatus httpStatus;
    private List<ApiEntityError> entityErrors = new ArrayList<>();

    public BusinessLogicException(String defaultMessage, HttpStatus httpStatus, List<ApiEntityError> entityErrors) {
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
        this.entityErrors = entityErrors;
    }

    public BusinessLogicException(String defaultMessage, HttpStatus httpStatus, ApiEntityError apiEntityError) {
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
        this.entityErrors.add(apiEntityError);
    }
}
