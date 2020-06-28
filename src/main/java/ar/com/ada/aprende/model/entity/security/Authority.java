package ar.com.ada.aprende.model.entity.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Role")
public class Authority {

    private static final String STRING = "STRING";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities", cascade = {CascadeType.ALL})
    private List<User> users;

    public Authority setId(Long id) {
        this.id = id;
        return this;
    }

    public Authority setName(AuthorityName name) {
        this.name = name;
        return this;
    }
}
