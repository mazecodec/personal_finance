package cl.mazecode.personalfinance.core.domain.entity;

import cl.mazecode.personalfinance.core.domain.enums.StoresTypes;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URI;

@Entity
@Table(name = "bill_items")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class BillItemEntity extends EntityBase implements Serializable {
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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bills_id")
    private BillEntity bill;
}
