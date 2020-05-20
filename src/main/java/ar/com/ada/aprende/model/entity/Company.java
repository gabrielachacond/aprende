package ar.com.ada.aprende.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nameCompany;

    @Column(nullable = false, length = 11)
    private Integer cuil;

    @Column(nullable = false, length = 100)
    private String typeCompany;

    @Column(nullable = false, length = 200)
    private String addressCompany;

    @Column(nullable = false, length = 100)
    @JsonFormat(pattern="YYYY")
    private LocalDate fundationYear;

    @Column(nullable = false, length = 20)
    private Integer contactNumber;

    @ManyToOne
    @JoinColumn(name = "TypeCategoryCompany_id", nullable = true)
    private TypeCategoryCompany typeCategoryCompany;

}
