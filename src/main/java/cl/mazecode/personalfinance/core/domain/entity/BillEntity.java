package cl.mazecode.personalfinance.core.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "bills")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    @NotNull
    private String description;
    @Column(precision = 2)
    private Float total;
    @Column(nullable = false)
    @NotNull
    private Instant createAt;
    @Column
    private Date updatedAt;
    @Column
    private Date deletedAt;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "accounts_id")
    @NotNull
    private AccountEntity account;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BillItemEntity> items;
}
