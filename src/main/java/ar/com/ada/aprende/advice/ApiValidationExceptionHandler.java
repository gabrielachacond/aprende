package ar.com.ada.aprende.advice;

import ar.com.ada.aprende.exception.ApiErrorsResponseBody;
import ar.com.ada.aprende.exception.ApiFieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<ApiFieldError> apiFieldErrors = fieldErrors
                .stream()
                .map(fieldError -> new ApiFieldError(
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getDefaultMessage())
                )
                .collect(Collectors.toList());

        ApiErrorsResponseBody apiErrorsResponseBody = new ApiErrorsResponseBody<ApiFieldError>(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                apiFieldErrors);

        return ResponseEntity
                .badRequest()
                .body(apiErrorsResponseBody);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // dividimos el mensaje por los saltos de lineas (\n) y lo almacenamos en una lista de string
        List<String> exMessagelines = Arrays.asList(ex.getMessage().split("\n"));

        // Del primer elemento de la lista exMessagelines (que es una string), lo dividimos por 2 puntos (:)
        // y lo almacenamos en otra lista de string
        List<String> messageLines = Arrays.asList(exMessagelines.get(0).split(":"));

        // de la lista messageLines, obenemos el ultimo elemento que nos indica el error que ocurrio
        String message = messageLines.get(messageLines.size() - 1).trim();

        // Del segundo elemento de la lista exMessagelines (que es una string), lo dividimos por 2 doble comillas (\")
        // y lo almacenamos en otra lista de string
        List<String> filedLines = Arrays.asList(exMessagelines.get(1).split("\""));

        // de la filedLines, obtenemos el segundo elemento que nos indica el campo que tiene la validacion.
        String field = filedLines.get(1);

        // se crea una lista con el error de tipo ApiFieldError y los datos de field y  message
        List<ApiFieldError> apiFieldErrors = Arrays.asList(new ApiFieldError(field, "", message));

        // se crea un obleto ApiErrorsResponseBody que contiene la lista apiFieldErrors
        // y la informacion de tiempo, status code y status response
        ApiErrorsResponseBody apiErrorsResponseBody = new ApiErrorsResponseBody(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                apiFieldErrors);

        // se retorna el response final de los errores.
        return ResponseEntity.badRequest().body(apiErrorsResponseBody);
    }
}