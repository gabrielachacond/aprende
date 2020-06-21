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

    @EmbeddedId
    private CourseHasParticipantId id;

    @ManyToOne
    @MapsId ("courseId")
    private Course course;

    @ManyToOne
    @MapsId ("participantId")
    private Participant participant;

    @Column(nullable = false, columnDefinition = "boolean")
    private Boolean courseHasFinish;

    @Column(nullable = false, columnDefinition = "boolean")
    private Boolean isScholaship;

    @Column(columnDefinition = "boolean")
    private Boolean isApproved;

    @Column()
    private Integer approvalRate;

    public CourseHasParticipant setId(CourseHasParticipantId id) {
        this.id = id;
        return  this;
    }

    public CourseHasParticipant setCourse(Course course) {
        this.course = course;
        return  this;
    }

    public CourseHasParticipant setParticipant(Participant participant) {
        this.participant = participant;
        return  this;
    }
}

