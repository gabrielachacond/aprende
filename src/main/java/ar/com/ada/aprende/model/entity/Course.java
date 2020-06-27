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

    @Column(nullable = false)
    private Integer purchasedCouponCounter;

    @Column(nullable = false)
    private Integer scholarshipCouponCounter;

    @ManyToOne
    @JoinColumn(name = "TypeCategoryCourse_id")
    private TypeCategoryCourse typeCategoryCourse;

    @ManyToOne
    @JoinColumn(name = "TypeModalityCourse_id")
    private TypeModalityCourse typeModalityCourse;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "Company_id")
    private Company company;

    @OneToMany(mappedBy = "course")
    private Set<CourseHasParticipant> courseHasParticipants;

    public Course setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
        return this;
    }

    public Course setDescriptionCourse(String descriptionCourse) {
        this.descriptionCourse = descriptionCourse;
        return this;
    }

    public Course setCostCourse(Integer costCourse) {
        this.costCourse = costCourse;
        return this;
    }

    public Course setHoursCourse(Integer hoursCourse) {
        this.hoursCourse = hoursCourse;
        return this;
    }

    public Course setTotalPaticipants(Integer totalPaticipants) {
        this.totalPaticipants = totalPaticipants;
        return this;
    }

    public Course setPlacesToScholarship(Integer placesToScholarship) {
        this.placesToScholarship = placesToScholarship;
        return this;
    }

    public Course setPurchasedCouponCounter(Integer purchasedCouponCounter) {
        this.purchasedCouponCounter = purchasedCouponCounter;
        return this;
    }

    public Course setScholarshipCouponCounter(Integer scholarshipCouponCounter) {
        this.scholarshipCouponCounter = scholarshipCouponCounter;
        return this;
    }

    public Course setTypeCategoryCourse(TypeCategoryCourse typeCategoryCourse) {
        this.typeCategoryCourse = typeCategoryCourse;
        return this;
    }

    public Course setTypeModalityCourse(TypeModalityCourse typeModalityCourse) {
        this.typeModalityCourse = typeModalityCourse;
        return this;
    }

    public Course setCompany(Company company) {
        this.company = company;
        return this;
    }
}