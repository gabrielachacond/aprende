package ar.com.ada.aprende.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ "timestamp", "status", "error", "errors" })
public class ApiErrorsResponseBody<T> {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Timestamp timestamp;
    private Integer status;
    private String error;
    private List<T> errors;

    public ApiErrorsResponseBody(Integer status, String error, List<T> errors) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status;
        this.error = error;
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ApiErrorsResponseBody {" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error=" + error +
                ", errors=" + errors +
                " }";
    }
}