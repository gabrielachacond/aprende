package ar.com.ada.aprende.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "lastName", "gender", "birthday", "address", "courseHasParticipants", "socioEconomyStudy"})
public class ParticipantDTO implements Serializable {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "gender is required")
    private String gender;

    @NotNull(message = "birthday is required")
    @Past(message = "birthday must be past date")
    private LocalDate birthday;

    @NotBlank(message = "address is required")
    private String address;

 //*   @Valid
   // @NotNull(message = "courseHasParticipant is required")
    private CourseHasParticipantDTO courseHasParticipant;

  //  @Valid
 //   @NotNull(message = "socioEconomyStudy is required")
    private SocioEconomyStudyDTO socioEconomyStudy;

}
/*
{
"name":"Carolina",
"lastName":"dugarte",
"gender":"femenino",
"birthday":"1992/",
"address":"viamonte2506",

}
*/
