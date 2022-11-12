package cl.mazecode.personalfinance.core.domain.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "possessions")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class PossessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, unique = true)
    @Length(max = 200)
    @NonNull
    private String email;
    @Column(length = 100)
    @Length(max = 100)
    @NonNull
    private String name;
    @Column(length = 100)
    @Length(max = 100)
    @NonNull
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @NotNull
    private UserEntity owner;
}
