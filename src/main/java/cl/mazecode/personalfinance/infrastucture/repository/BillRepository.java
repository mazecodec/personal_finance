package cl.mazecode.personalfinance.infrastucture.repository;

import cl.mazecode.personalfinance.core.domain.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
}
