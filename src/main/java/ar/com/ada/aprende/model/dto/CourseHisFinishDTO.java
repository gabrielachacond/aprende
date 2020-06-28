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
@JsonPropertyOrder({"courseHasFinish"})
public class CourseHisFinishDTO implements Serializable {

    @NotNull(message = "course Has Finish is required")
    private Boolean courseHasFinish;
}

/*
http://localhost:8080/courses/1/partipants/1/finalize
{

 "CourseHasFinish": false,
 "isScholaship": true,
 "isApproved": true,
    "approvalRate": 75
}
 */
