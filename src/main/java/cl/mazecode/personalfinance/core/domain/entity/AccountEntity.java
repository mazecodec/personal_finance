package cl.mazecode.personalfinance.core.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull
    private Instant createAt;
    @Column
    private Date updatedAt;
    @Column
    private Date deletedAt;
    @OneToOne
    @NotNull
    private UserEntity userAccount;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BillEntity> bills;
}
