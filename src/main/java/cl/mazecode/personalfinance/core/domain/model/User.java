package cl.mazecode.personalfinance.core.domain.model;

import cl.mazecode.personalfinance.core.application.validation.PasswordMatches;
import cl.mazecode.personalfinance.core.application.validation.ValidPassword;
import cl.mazecode.personalfinance.core.domain.entity.AccountEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@PasswordMatches
public class User implements ModelI {
    @Id
    private Long id;
    @NonNull
    @NotNull
    @NotEmpty(message = "Email is required.")
    @Length(max = 200)
    private String email;
    @NonNull
    @NotNull
    @Length(max = 100)
    private String name;
    @NonNull
    @NotNull
    @Length(max = 100)
    private String lastName;
    @NotEmpty(message = "Password is required.")
    @ValidPassword
    private String password;
    @Transient
    @NotEmpty(message = "Password confirmation is required.")
    private String passwordConfirmation;
    private AccountEntity account;
    @NonNull
    @NotNull
    @Builder.Default
    private Instant createAt = Instant.now();
    private Date updatedAt;
    private Date deletedAt;
}
