package ar.com.ada.aprende.model.dto.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JwtAuthRequestBody {

    @Size(message = "username must be between 3 and 20 characters", min = 3, max = 20)
    @NotBlank(message = "username is required")
    private String username;

    @Size(message = "password must be at least 4 characters long", min = 4)
    @NotBlank(message = "password is required")
    private String password;
}
