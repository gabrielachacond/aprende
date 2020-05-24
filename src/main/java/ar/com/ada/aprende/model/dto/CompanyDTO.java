package ar.com.ada.aprende.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonPropertyOrder({"id", "nameCompany", "cuil", "typeCompany", "addressCompany", "fundationYear", "contactNumber"})
public class CompanyDTO implements Serializable {

    private Long id;

    @NotBlank(message = "nameCompany is required")
    private String nameCompany;

    @NotBlank(message = "cuil is required")
    private Integer cuil;

    @NotBlank(message = "typeCompany is required")
    private String typeCompany;

    @NotBlank(message = "addressCompany is required")
    private String addressCompany;

    @NotNull(message = "fundationYear is required")
    @Past(message = "fundationYear must be past date")
    @JsonFormat(pattern = "YYYY")
    private LocalDate fundationYear;

    @NotBlank(message = "contactNumber is required")
    private Integer contactNumber;

    @Valid
    private TypeCategoryCompanyDTO typeCategoryCompany;
}
