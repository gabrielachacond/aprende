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
@JsonPropertyOrder({"isApproved", "approvalRate"})
public class CourseScholarshipApprovalDTO implements Serializable {

    @NotNull(message = "isApproved is required")
    private Boolean isApproved;

    @NotNull(message = "approvalRate is required")
    private Integer approvalRate;
}

/*
 Para el PM dio 200 ok

 http://localhost:8080/courses/1/partipants/1/approval

{
    "isApproved": true,
    "approvalRate": 75
}

{
 en la segunda prueba 200 ok

 "CourseHasFinish": false,
 "isScholaship": true,
 "isApproved": true,
    "approvalRate": 75
}
*/
