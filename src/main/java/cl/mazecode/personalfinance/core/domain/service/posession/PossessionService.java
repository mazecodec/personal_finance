package cl.mazecode.personalfinance.core.domain.service.posession;

import cl.mazecode.personalfinance.core.domain.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.model.Possession;

import java.util.Optional;

public interface PossessionService {
    Optional<Possession> find(Long id) throws NotFoundException;

    Possession save(Possession possession);
}
