package ar.com.ada.aprende.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "category"})
public class TypeCategoryCompanyDTO implements Serializable {

    @NotNull(message = "id category Type is required")
    private Long id;

    private String caterory;

}
