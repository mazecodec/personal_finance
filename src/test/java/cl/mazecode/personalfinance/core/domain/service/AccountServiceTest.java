package cl.mazecode.personalfinance.core.domain.service;

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

//    @Test
//    void givenPrePopulateData_All_ShouldReturnAUserList() throws NotFoundException {
//        Long id = 1L;
//
//        assertThat(this.accountService.find(id)
//                                      .orElseThrow()
//                                      .getId()).isGreaterThanOrEqualTo(id);
//    }

    @Test
    void givenANewAccount_Save_ShouldReturnANewAccount() throws EmailExistsException, NotFoundException {
        User newUser = User.builder()
                           .id(1L)
                           .name("Test")
                           .lastName("User")
                           .email("test@email.com")
                           .build();

        User userFromDB = this.userService.save(newUser);

        Long newAccountId = 1L;
        Account newAccount = Account.builder()
                                    .id(newAccountId)
                                    .userAccount(newUser)
                                    .build();

        this.accountService.create(newAccount);

        Account accountFromDB = this.accountService.find(newAccountId)
                                                   .orElseThrow();

        assertThat(accountFromDB).isNotNull();
        assertThat(newAccount).isEqualTo(accountFromDB);
        assertThat(accountFromDB.getId()).isGreaterThanOrEqualTo(newAccountId);
    }
}
