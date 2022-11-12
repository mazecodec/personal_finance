package cl.mazecode.personalfinance.core.domain.repository;

import cl.mazecode.personalfinance.core.domain.entity.PossessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PossessionRepository extends JpaRepository<PossessionEntity, Long> {
}
