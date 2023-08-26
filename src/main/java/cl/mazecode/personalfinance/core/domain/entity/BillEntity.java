package cl.mazecode.personalfinance.core.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bills")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class BillEntity extends EntityBase implements Serializable {
    @Column(nullable = false, length = 100, unique = true)
    private String sku;
    @Column(nullable = false, length = 200)
    @NotNull
    private String description;
    @Column(precision = 2)
    private Float total;
    @Column
    private boolean isDone;
    @Column
    private boolean isArchived;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "accounts_id")
    private AccountEntity account;
    @OneToMany(mappedBy = "bill", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BillItemEntity> items = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
        this.isDone = false;
        this.isArchived = false;

        if (null == this.sku) {
            Instant timestamp = Instant.now();
            this.sku = timestamp.getNano() + this.id + "";

            if (this.sku.length() > 100) {
                this.sku = this.sku.substring(0, 100);
            }
        }
    }

}
