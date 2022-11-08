package cl.mazecode.personalfinance.core.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    @NonNull
    private String name;
    @Column(length = 100)
    @NonNull
    private String lastName;
    @Column(length = 100, unique = true)
    @NonNull
    private String email;
}
