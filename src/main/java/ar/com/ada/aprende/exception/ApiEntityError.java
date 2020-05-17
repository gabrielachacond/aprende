package ar.com.ada.aprende.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiEntityError {
    private String entity;
    private String code;
    private String message;

    public ApiEntityError(String entity, String code, String message) {
        this.entity = entity;
        this.code = code;
        this.message = message;
    }
}