package cl.mazecode.personalfinance.core.application.service;

import cl.mazecode.personalfinance.core.application.exception.EmailExistsException;
import cl.mazecode.personalfinance.core.application.exception.NotFoundException;
import cl.mazecode.personalfinance.core.application.service.account.AccountService;
import cl.mazecode.personalfinance.core.application.service.user.UserService;
import cl.mazecode.personalfinance.core.domain.model.Account;
import cl.mazecode.personalfinance.core.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;


    @Test
    public void givenANewAccount_Save_ShouldReturnANewAccount() throws EmailExistsException, NotFoundException {
        User newUser = User.builder()
                           .id(1L)
                           .name("Test")
                           .lastName("User")
                           .email("test@email.com")
                           .build();

        this.userService.save(newUser);

        final Long accountId = 1L;
        Account newAccount = Account.builder()
                                    .id(accountId)
                                    .userAccount(newUser)
                                    .build();

        this.accountService.save(newAccount);

        Account accountFromDB = this.accountService.find(accountId)
                                                   .orElseThrow();

        assertThat(accountFromDB).isNotNull();
        assertThat(newAccount).isEqualTo(accountFromDB);
        assertThat(accountFromDB.getId()).isGreaterThanOrEqualTo(accountId);
    }
}
