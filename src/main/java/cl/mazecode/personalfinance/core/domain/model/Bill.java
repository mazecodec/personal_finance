package cl.mazecode.personalfinance.core.domain.model;

import cl.mazecode.personalfinance.core.domain.entity.BillItemEntity;
import lombok.*;

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
public class Bill implements ModelI {
    @Id
    private Long id;
    @NonNull
    @NotNull
    private String description;
    @NonNull
    @NotNull
    @Builder.Default
    private Float total = 0F;
    @NonNull
    @NotNull
    @Builder.Default
    private Instant createAt = Instant.now();
    private Date updatedAt;
    private Date deletedAt;
    @Builder.Default
    private Set<BillItemEntity> items = new HashSet<>();
}