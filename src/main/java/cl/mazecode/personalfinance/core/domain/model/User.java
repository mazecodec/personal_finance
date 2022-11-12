package cl.mazecode.personalfinance.core.domain.model;

import cl.mazecode.personalfinance.core.application.validation.PasswordMatches;
import cl.mazecode.personalfinance.core.application.validation.ValidPassword;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "Email is required.")
    @Length(max = 200)
    private String email;
    @NonNull
    @Length(max = 100)
    private String name;
    @NonNull
    @Length(max = 100)
    private String lastName;
    @NotEmpty(message = "Password is required.")
    @ValidPassword
    private String password;
    @Transient
    @NotEmpty(message = "Password confirmation is required.")
    private String passwordConfirmation;

}
