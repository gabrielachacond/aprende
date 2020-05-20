package ar.com.ada.aprende.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "SocioEconomyStudy")
public class SocioEconomyStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

}

