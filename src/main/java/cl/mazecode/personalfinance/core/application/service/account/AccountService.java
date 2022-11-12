package cl.mazecode.personalfinance.core.application.service.account;

import cl.mazecode.personalfinance.core.application.exception.NotDeletedException;
import cl.mazecode.personalfinance.core.application.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.model.Account;

import java.util.Optional;

public interface AccountService {
    Account create(Account account);

    Optional<Account> find(Long id) throws NotFoundException;

    boolean delete(Long id) throws NotFoundException, NotDeletedException;
}
