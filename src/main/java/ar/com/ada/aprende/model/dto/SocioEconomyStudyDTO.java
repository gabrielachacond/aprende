package ar.com.ada.aprende.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "isStudying", "isWorking", "isMonthlyAmount", "isFamilyCharge", "howManyFamily", "participant"})
public class SocioEconomyStudyDTO implements Serializable {

    public Long id;

    @NotNull(message = "isStudying is required")
    private Boolean isStudying;

    @NotNull(message = "isWorking is required")
    private Boolean isWorking;

    @NotNull(message = "isMonthlyAmount is required")
    private Integer isMonthlyAmount;

    @NotNull(message = "isFamilyCharge is required")
    private Boolean isFamilyCharge;

    @NotNull(message = "howManyFamily is required")
    private Integer howManyFamily;

    @NotNull(message = "id participant is required")
    private Long participantId;

    private ParticipantDTO participant;
}
