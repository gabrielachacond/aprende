package ar.com.ada.aprende.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;
import java.util.List;

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
    private Long cuil;

    @Column(nullable = false, length = 100)
    private String typeCompany;

    @Column(nullable = false, length = 200)
    private String addressCompany;

    @Column(nullable = false, columnDefinition = "YEAR")
    private Year fundationYear;

    @Column(nullable = false, length = 20)
    private Integer contactNumber;

    @ManyToOne
    @JoinColumn(name = "TypeCategoryCompany_id")
    private TypeCategoryCompany typeCategoryCompany;

    @OneToMany(mappedBy = "company")
    private List<CompanyRepresentative> companyRepresentatives;

    @OneToMany(mappedBy = "company")
    private List<Course> courses;

    public Company(Long id) {
        this.id = id;
    }

}
