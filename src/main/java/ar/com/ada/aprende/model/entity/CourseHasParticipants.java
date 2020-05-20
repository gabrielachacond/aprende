package ar.com.ada.aprende.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "CourseHasParticipants")
public class CourseHasParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 100)
    private Boolean courseHasFinish;

    @Column(nullable = false, length = 100)
    private Boolean isScholaship;

    @Column(nullable = false, length = 100)
    private Boolean isApproved;


    @OneToMany(mappedBy = "CourseHasParticipants")
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name = "Course_id", nullable = true)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "Participants_id", nullable = true)
    private Participants participants;



}

