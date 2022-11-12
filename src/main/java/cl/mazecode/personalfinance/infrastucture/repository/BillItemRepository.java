package cl.mazecode.personalfinance.infrastucture.repository;

import cl.mazecode.personalfinance.core.domain.entity.BillItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillItemRepository extends JpaRepository<BillItemEntity, Long> {
}
