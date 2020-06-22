package ar.com.ada.aprende.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"isApproved"})
public class CourseScholarshipApprovalDTO {

    @NotNull(message = "isApproved is required")
    private Boolean isApproved;

    private Integer approvalRate;
}

/*
 Para el PM

http://localhost:8080/courses/1/participants/1 y localhost:8080/courses/1/participants/1/

{
    "isApproved": true
}
*/
