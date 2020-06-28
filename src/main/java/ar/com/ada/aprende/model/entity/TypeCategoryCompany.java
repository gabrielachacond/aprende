package ar.com.ada.aprende.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TypeCategoryCompany")
public class TypeCategoryCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String caterory;

    @OneToMany(mappedBy = "typeCategoryCompany")
    private List<Company> companies;


    public TypeCategoryCompany setId(Long id) {
        this.id = id;
        return this;
    }

    public TypeCategoryCompany setCaterory(String caterory) {
        this.caterory = caterory;
        return this;
    }
}

