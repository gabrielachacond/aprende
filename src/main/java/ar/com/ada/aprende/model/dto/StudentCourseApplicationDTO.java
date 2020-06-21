package ar.com.ada.aprende.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"isScholaship"})
public class StudentCourseApplicationDTO implements Serializable {

    @NotNull(message = "isScholaship is required")
    private Boolean isScholaship;

}
/*
http://localhost:8080/courses/1/partipants/1
// se hizo la prueba y dio ok lo creo
/*
{
    "isScholaship": true
}
*/