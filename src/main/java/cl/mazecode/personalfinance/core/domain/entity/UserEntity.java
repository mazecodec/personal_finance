package cl.mazecode.personalfinance.core.domain.entity;

import cl.mazecode.personalfinance.core.application.validation.ValidPassword;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, unique = true)
    @NotNull
    @Length(max = 200)
    private String email;
    @Column(length = 100)
    @Length(max = 100)
    @NotNull
    private String name;
    @Column(length = 100)
    @Length(max = 100)
    @NotNull
    private String lastName;
    @Column
    @ValidPassword
    private String password;
    @OneToOne(cascade = CascadeType.PERSIST)
    private AccountEntity account;
    @Column
    @NotNull
    @Builder.Default
    private Instant createAt = Instant.now();
    @Column
    private Date updatedAt;
    @Column
    private Date deletedAt;
}
