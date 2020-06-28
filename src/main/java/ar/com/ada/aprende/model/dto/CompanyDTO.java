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
import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "nameCompany", "cuil", "typeCompany", "addressCompany", "fundationYear", "contactNumber"})
public class CompanyDTO implements Serializable {

    private Long id;

    @NotBlank(message = "nameCompany is required")
    private String nameCompany;

    @NotNull(message = "cuil is required")
    private Long cuil;

    @NotBlank(message = "typeCompany is required")
    private String typeCompany;

    @NotBlank(message = "addressCompany is required")
    private String addressCompany;

    @NotNull(message = "fundationYear is required")
    @Past(message = "fundationYear must be past date")
    private Year fundationYear;

    @NotNull(message = "contactNumber is required")
    private Integer contactNumber;

    @Valid
    @NotNull(message = "typeCategoryCompany is required")
    private TypeCategoryCompanyDTO typeCategoryCompany;
}

