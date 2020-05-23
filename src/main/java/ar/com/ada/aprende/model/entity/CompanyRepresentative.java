package ar.com.ada.aprende.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "CompanyRepresentative")
public class CompanyRepresentative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String PositionInTheCompany;

    @Column(nullable = false, length = 200)
    private String email;

    @ManyToOne
    @JoinColumn(name = "Company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "TypeDocument_id")
    private TypeDocument typeDocument;

}
