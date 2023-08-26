package cl.mazecode.personalfinance.core.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "packs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackEntity extends EntityBase implements Serializable {
    @Column(length = 100, nullable = false)
    @NotNull
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private UserEntity user;
    @OneToMany(targetEntity = EventEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "packs_events", joinColumns = {@JoinColumn(name = "groups_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "events_id", referencedColumnName = "id")})
    private Set<EventEntity> events;

}
