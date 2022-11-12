package cl.mazecode.personalfinance.core.domain.entity;

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
    @Column(nullable = false, precision = 2)
    @NotNull
    private Float cost;
    @Column(length = 100)
    private String store;
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
