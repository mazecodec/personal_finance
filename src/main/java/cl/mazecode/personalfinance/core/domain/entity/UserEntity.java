package cl.mazecode.personalfinance.core.domain.entity;

import cl.mazecode.personalfinance.core.application.validation.ValidPassword;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class UserEntity extends EntityBase implements Serializable {
    @Column(length = 100, unique = true)
    @Length(max = 100)
    private String email;
    @Column(length = 100)
    @Length(max = 100)
    @NotNull
    private String name;
    @Column(length = 100)
    @Length(max = 100)
    @NotNull
    private String lastName;
    @Column
    @ValidPassword
    private String password;
    @OneToOne(mappedBy = "userAccount", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AccountEntity account;
}
