package ar.com.ada.aprende.model.dto;

import ar.com.ada.aprende.model.entity.Course;
import ar.com.ada.aprende.model.entity.Participant;
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
@JsonPropertyOrder({"course", "participant", "courseHasFinish", "isScholaship", "isApproved"})
public class CourseHasParticipantDTO implements Serializable {

    private Course course;
    private Participant participant;

    @NotNull(message = "courseHasFinish is required")
    private Boolean courseHasFinish;

    @NotNull(message = "isScholaship is required")
    private Boolean isScholaship;

    @NotNull(message = "isApproved is required")
    private Boolean isApproved;

}
