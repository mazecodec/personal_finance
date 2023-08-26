package cl.mazecode.personalfinance.core.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class EventEntity extends EntityBase implements Serializable {
    @Column(length = 100, nullable = false)
    @NotNull
    @NonNull
    private String title;
    @Column(length = 250)
    private String description;
    @Column
    private Instant date;
    @ManyToMany
    private Set<UserEntity> attendees;
}
