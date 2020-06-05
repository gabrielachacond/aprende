package ar.com.ada.aprende.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "CourseHasParticipant")
public class CourseHasParticipant implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "Course_id")
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name = "Participants_id")
    private Participant participant;

    @Column(nullable = false, length = 100)
    private Boolean courseHasFinish;

    @Column(nullable = false, length = 100)
    private Boolean isScholaship;

    @Column(nullable = false, length = 100)
    private Boolean isApproved;

}

