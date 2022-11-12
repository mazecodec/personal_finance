package cl.mazecode.personalfinance.core.domain.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Possession implements ModelI {
    @Id
    private Long id;
    @Length(max = 200)
    @NonNull
    private String email;
    @Length(max = 100)
    @NonNull
    private String name;
    @Length(max = 100)
    @NonNull
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @NotNull
    private User owner;
}
