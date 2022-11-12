package cl.mazecode.personalfinance.core.application.service.account;

import cl.mazecode.personalfinance.core.application.exception.NotDeletedException;
import cl.mazecode.personalfinance.core.application.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.entity.AccountEntity;
import cl.mazecode.personalfinance.core.domain.entity.UserEntity;
import cl.mazecode.personalfinance.core.domain.model.Account;
import cl.mazecode.personalfinance.core.domain.model.User;
import cl.mazecode.personalfinance.infrastucture.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository repository;

    @Override
    public Account create(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(account.getUserAccount(), userEntity);
        BeanUtils.copyProperties(account, accountEntity);

        accountEntity.setUserAccount(userEntity);

        AccountEntity fromDb = this.repository.save(accountEntity);

        return account;
    }

    @Override
    public Optional<Account> find(Long id) throws NotFoundException {
        AccountEntity accountEntity = this.repository.findById(id)
                                                     .orElseThrow(NotFoundException::new);
        User user = new User();
        Account account = new Account();
        BeanUtils.copyProperties(accountEntity, account);
        BeanUtils.copyProperties(accountEntity.getUserAccount(), user);

        account.setUserAccount(user);

        return Optional.of(account);
    }

    @Override
    public boolean delete(Long id) throws NotFoundException, NotDeletedException {
        AccountEntity accountEntity = this.repository.findById(id)
                                                     .orElseThrow(NotFoundException::new);

        try {
            this.repository.delete(accountEntity);
        } catch (Exception e) {
            throw new NotDeletedException();
        }

        return true;
    }
}
