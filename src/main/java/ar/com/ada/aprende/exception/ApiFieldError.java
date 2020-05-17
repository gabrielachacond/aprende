package ar.com.ada.aprende.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"field", "code", "message"})
public class ApiFieldError {

    private String field;
    private String code;
    private String message;

    public ApiFieldError(String field, String code, String message) {
        this.field = field;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiFieldError {" +
                "field=" + field +
                ", code=" + code +
                ", message=" + message + '\'' +
                " }";
    }
}