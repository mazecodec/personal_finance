package cl.mazecode.personalfinance.core.domain.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Account implements ModelI {
    @Id
    private Long id;
    @NonNull
    @NotNull
    @Length(max = 100)
    private String description;
    @NonNull
    @NotNull
    private User userAccount;
    @NotNull
    @Builder.Default
    private Instant createAt = Instant.now();
    private Date updatedAt;
    private Date deletedAt;
    @Builder.Default
    private Set<Bill> bills = new HashSet<>();
}
