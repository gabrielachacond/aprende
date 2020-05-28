package ar.com.ada.aprende.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "type"})
public class TypeDocumentDTO implements Serializable {

    @NotNull(message = "id document Type is required")
    private Long id;

    private String type;

}
