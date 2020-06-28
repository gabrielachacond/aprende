package ar.com.ada.aprende.model.dto.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@JsonPropertyOrder({"type", "toke", "createAt"})
@NoArgsConstructor
public class JwtAuthResponseBody {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSXXX")
    private final Date createAt = new Date();

    private String type;

    private String token;

    public JwtAuthResponseBody(String type, String token) {
        this.type = type;
        this.token = token;
    }

    public JwtAuthResponseBody setType(String type) {
        this.type = type;
        return this;
    }

    public JwtAuthResponseBody setToken(String token) {
        this.token = token;
        return this;
    }

}
