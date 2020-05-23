package ar.com.ada.aprende.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nameCourse;

    @Column(nullable = false, length = 100)
    private String descriptionCourse;

    @Column(nullable = false, length = 100)
    private Integer costCourse;

    @Column(nullable = false, length = 100)
    private Integer hoursCourse;

    @Column(nullable = false, length = 100)
    private Integer totalPaticipants;

    @Column(nullable = false, length = 100)
    private Integer placesToScholarship;

    @ManyToOne
    @JoinColumn(name = "TypeCategoryCourse_id", nullable = true)
    private TypeCategoryCompany typeCategoryCompany;

    @ManyToOne
    @JoinColumn(name = "TypeModalityCourse_id", nullable = true)
    private TypeModalityCourse typeModalityCourse;

    @ManyToOne
    @JoinColumn(name = "Company_id", nullable = true)
    private Company company;


    @OneToMany(mappedBy = "course")
    private Set<CourseHasParticipants> courseHasParticipants;


}