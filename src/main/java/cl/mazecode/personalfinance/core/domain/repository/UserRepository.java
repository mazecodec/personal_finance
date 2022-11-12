package cl.mazecode.personalfinance.core.domain.repository;

import cl.mazecode.personalfinance.core.domain.entity.UserEntity;
import cl.mazecode.personalfinance.core.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    User findByEmail(String email);
}
