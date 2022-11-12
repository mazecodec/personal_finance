package cl.mazecode.personalfinance.core.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groups")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    @NonNull
    private String name;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String stateOrProvince;
    @Column
    private String country;
    @Column
    private String postalCode;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<EventEntity> events;
}
