package cl.mazecode.personalfinance.presentation.api.controller;

import cl.mazecode.personalfinance.core.domain.model.Account;
import cl.mazecode.personalfinance.core.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Transactional
public class AccountControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenANewUser_PostUsersEndPoint_ShouldAddNewUsers() {
        String uri = String.format("http://localhost:%s/api/v1/accounts", this.port);
        String uriUsers = String.format("http://localhost:%s/api/v1/users", this.port);

        User newUser = User.builder()
                           .id(1L)
                           .name("Test")
                           .lastName("User")
                           .email("test@email.com")
                           .build();

        ResponseEntity<User> responseUser = this.restTemplate.postForEntity(uriUsers, newUser, User.class);
        User userFromDB = responseUser.getBody();

        assertThat(userFromDB).isNotNull();

        final Long accountId = 1L;
        Account newAccount = Account.builder()
                                    .id(accountId)
                                    .userAccount(userFromDB)
                                    .build();

        ResponseEntity<Account> response = this.restTemplate.postForEntity(uri, newAccount, Account.class);

        Account accountFromDB = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountFromDB).isNotNull();
        assertEquals(accountFromDB.getId(), newAccount.getId(), "Account id should be same");
    }
}
