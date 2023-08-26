package cl.mazecode.personalfinance.core.application.service;

import cl.mazecode.personalfinance.core.application.service.account.AccountService;
import cl.mazecode.personalfinance.core.application.service.bill.BillService;
import cl.mazecode.personalfinance.core.domain.model.Account;
import cl.mazecode.personalfinance.core.domain.model.Bill;
import cl.mazecode.personalfinance.core.domain.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class BillServiceTest {
    private static Account account;
    @Autowired
    private BillService billService;

    @Autowired
    private AccountService service;

    @BeforeAll
    public static void beforeAll() {
        User userAccount = User.builder()
                               .id(1L)
                               .name("NameTest")
                               .lastName("LastNameTest")
                               .email("test@test.com")
                               .build();

        account = Account.builder()
                         .id(1L)
                         .description("AccountTest")
                         .createAt(Instant.now())
                         .userAccount(userAccount)
                         .build();
    }

    @Test
    public void givenCreateANewBill_Save_ShouldReturnNewBill() {
        Bill newBill = Bill.builder()
                           .id(1L)
                           .description("BillTest-Description")
                           .total(0F)
                           .items(new HashSet<>())
                           .build();

        Bill billFromDB = this.billService.save(newBill);

        assertThat(billFromDB).isNotNull();
        assertThat(billFromDB.getId()).isGreaterThanOrEqualTo(1L);
        assertEquals(billFromDB.getDescription(), newBill.getDescription(), "Bill description should be same");
    }
}
