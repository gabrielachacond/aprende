package ar.com.ada.aprende.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TypeCategoryCourse")
public class TypeCategoryCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String category;

    @OneToMany(mappedBy = "typeCategoryCourse")
    private List<Course> courses;

    public TypeCategoryCourse setId(Long id) {
        this.id = id;
        return this;
    }

    public TypeCategoryCourse setCategory(String category) {
        this.category = category;
        return this;
    }
}
