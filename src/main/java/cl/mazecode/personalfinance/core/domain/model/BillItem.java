package cl.mazecode.personalfinance.core.domain.model;

import cl.mazecode.personalfinance.core.domain.entity.BillEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class BillItem implements ModelI {
    @Id
    private Long id;
    @Length(max = 200)
    @NonNull
    @NotNull
    private String description;
    @NonNull
    @NotNull
    @Builder.Default
    private Float cost = 0F;
    @Length(max = 100)
    private String store;
    private URI uri;
    @NonNull
    @NotNull
    @Builder.Default
    private Instant createAt = Instant.now();
    private Date updatedAt;
    private Date deletedAt;
    @NonNull
    @NotNull
    private BillEntity bill;

}
