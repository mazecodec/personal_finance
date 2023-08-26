package cl.mazecode.personalfinance.core.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class AccountEntity extends EntityBase implements Serializable {
    @Column(nullable = false, length = 100)
    @NotNull
    private String description;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private UserEntity userAccount;
    @OneToMany(
            targetEntity = BillEntity.class,
            mappedBy = "account",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private Set<BillEntity> bills = new HashSet<>();
}
