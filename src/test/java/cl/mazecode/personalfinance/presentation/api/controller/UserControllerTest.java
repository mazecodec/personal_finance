package cl.mazecode.personalfinance.presentation.api.controller;

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
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenUsers_GetUsersEndPoint_ShouldReturnUsersList() {
        String uri = String.format("http://localhost:%s/api/v1/users", this.port);

        ResponseEntity<User[]> response = this.restTemplate.getForEntity(uri, User[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void givenANewUser_PostUsersEndPoint_ShouldAddNewUsers() {
        String uri = String.format("http://localhost:%s/api/v1/users", this.port);

        User newUser = User.builder()
                           .id(1L)
                           .name("Test")
                           .lastName("User")
                           .email("test@email.com")
                           .build();

        ResponseEntity<User> response = this.restTemplate.postForEntity(uri, newUser, User.class);

        User userFromDB = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(userFromDB).isNotNull();
        assertEquals(userFromDB.getEmail(), newUser.getEmail(), "Users email should be same");
    }
}
