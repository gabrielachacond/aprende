package ar.com.ada.aprende.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "lastName", "PositionInTheCompany", "email"})
public class CompanyRepresentativeDTO implements Serializable {

    public Long id;

    @NotBlank(message = "name company representative is required")
    private String name;

    @NotBlank(message = "lastName company representative is required")
    private String lastName;

    @NotBlank(message = "position in the company representative is required")
    private String positionInTheCompany;

    @NotBlank(message = "email company representative is required")
    private String email;

    @NotNull(message = "id company is required")
    private Long companyId;

    @Valid
    @NotNull(message = "typeDocument is required")
    private TypeDocumentDTO typeDocument;

}
