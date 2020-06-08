package ar.com.ada.aprende.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 20)
    private String gender;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDate birthday;

    @Column(nullable = false, length = 200)
    private String address;

    @OneToMany(mappedBy = "participant")
    private List<CourseHasParticipant> courseHasParticipants;

    @OneToOne(mappedBy = "participant")
    private SocioEconomyStudy socioEconomyStudy;
}
