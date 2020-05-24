package ar.com.ada.aprende.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "SocioEconomyStudy")
public class SocioEconomyStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 100)
    private Boolean isStudying;

    @Column(nullable = false, length = 100)
    private Boolean isWorking;

    @Column(nullable = false, length = 100)
    private Integer isMonthlyAmount;

    @Column(nullable = false, length = 100)
    private Boolean isFamilyCharge;

    @Column(nullable = false, length = 100)
    private Integer howManyFamily;

    @OneToOne
    @JoinColumn(name = "Participants_id", referencedColumnName = "id")
    private Participants participants;




}

