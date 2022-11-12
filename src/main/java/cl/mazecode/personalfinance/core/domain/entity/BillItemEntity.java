package cl.mazecode.personalfinance.core.domain.entity;

import cl.mazecode.personalfinance.core.domain.enums.StoresTypes;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "bill_items")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class BillItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    @NotNull
    private String description;
    @Column(length = 50)
    private StoresTypes store;
    @Column(nullable = false, precision = 2)
    @NotNull
    private Float cost;
    @Column
    @NotNull
    private Float parts;
    @Column
    @NotNull
    private boolean isPayInParts;
    @Column
    private URI uri;
    @Column(nullable = false)
    @NotNull
    private Instant createAt;
    @Column
    private Date updatedAt;
    @Column
    private Date deletedAt;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private BillEntity bill;
}
