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

    public Participant setName(String name) {
        this.name = name;
        return this;
    }

    public Participant setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Participant setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Participant setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public Participant setAddress(String address) {
        this.address = address;
        return this;
    }
}
