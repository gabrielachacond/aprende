package ar.com.ada.aprende.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TypeModalityCourse")
public class TypeModalityCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String modality;

    @OneToMany(mappedBy = "typeModalityCourse")
    private List<Course> courses;

    public TypeModalityCourse setId(Long id) {
        this.id = id;
        return this;
    }

    public TypeModalityCourse setModality(String modality) {
        this.modality = modality;
        return this;
    }
}
