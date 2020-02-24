package tech.bestwebshop.api.authorizationservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NonNull
    private int id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String typ;

    @Column(nullable = false)
    @NonNull
    private int level;
}
