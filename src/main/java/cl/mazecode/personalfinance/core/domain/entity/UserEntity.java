package cl.mazecode.personalfinance.core.domain.entity;

import cl.mazecode.personalfinance.core.application.validation.ValidPassword;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "users")

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, unique = true)
    @NonNull
    @Length(max = 200)
    private String email;
    @Column(length = 100)
    @Length(max = 100)
    @NonNull
    private String name;
    @Column(length = 100)
    @Length(max = 100)
    @NonNull
    private String lastName;
    @Column
    @ValidPassword
    private String password;
}
