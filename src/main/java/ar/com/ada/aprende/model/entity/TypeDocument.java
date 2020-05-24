package ar.com.ada.aprende.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TypeDocument")
public class TypeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String type;

    @OneToMany(mappedBy = "typeDocument")
    private List<CompanyRepresentative> companyRepresentatives;
}
