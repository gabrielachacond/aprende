package ar.com.ada.aprende.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "nameCourse", "descriptionCourse", "costCourse", "hoursCourse", "totalPaticipants", "placesToScholarship", "typeModalityCourse", "typeModalityCourse", "company", "courseHasParticipants"})
public class CourseDTO implements Serializable {

    private Long id;

    @NotBlank(message = "nameCourse is required")
    private String nameCourse;

    @NotBlank(message = "descriptionCourse is required")
    private String descriptionCourse;

    @NotNull(message = "costCourse is required")
    private Integer costCourse;

    @NotNull(message = "hoursCourse is required")
    private Integer hoursCourse;

    @NotNull(message = "totalPaticipants is required")
    private Integer totalPaticipants;

    @NotNull(message = "placesToScholarship is required")
    private Integer placesToScholarship;

    private Integer purchasedCouponCounter;

    private Integer scholarshipCouponCounter;

    @Valid
    @NotNull(message = "typeCategoryCourse is required")
    private TypeCategoryCourseDTO typeCategoryCourse;

    @Valid
    @NotNull(message = "typeModalityCourse is required")
    private TypeModalityCourseDTO typeModalityCourse;

    private CompanyDTO company;

    @NotNull(message = "id company is required")
    private Long companyId;

    private Set<CourseHasParticipantDTO> courseHasParticipants;
}
/*
{
    "nameCourse": "java",
    "descriptionCourse": "aprende en 5 meses java",
    "costCourse": 100000,
    "hoursCourse": 300,
    "totalPaticipants": 20,
    "placesToScholarship": 5,
    "companyId":1,
    "purchasedCouponCounter": 1,
    "scholarshipCouponCounter":2,

   "typeModalityCourse":{
       "id":1
    },
    "typeCategoryCourse":{
        "id": 2
    }
}
*/