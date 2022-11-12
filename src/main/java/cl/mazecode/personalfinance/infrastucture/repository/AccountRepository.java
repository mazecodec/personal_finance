package cl.mazecode.personalfinance.infrastucture.repository;

import cl.mazecode.personalfinance.core.domain.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
