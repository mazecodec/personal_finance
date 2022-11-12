package cl.mazecode.personalfinance.core.domain.service;

import cl.mazecode.personalfinance.core.domain.exception.EmailExistsException;
import cl.mazecode.personalfinance.core.domain.model.User;
import cl.mazecode.personalfinance.core.domain.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void givenPrePopulateData_All_ShouldReturnAUserList() {
        assertThat(this.userService.all()
                                   .size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void givenANewUser_Save_ShouldReturnANewUser() throws EmailExistsException {
        User newUser = User.builder()
                           .id(1L)
                           .name("Test")
                           .lastName("User")
                           .email("test@email.com")
                           .build();

        User userFromDB = this.userService.save(newUser);

        assertThat(this.userService.all()
                                   .size()).isGreaterThanOrEqualTo(1);

        assertThat(userFromDB).isNotNull();
        assertEquals(userFromDB.getEmail(), newUser.getEmail(), "Users email should be same");

    }
}
