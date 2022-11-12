package cl.mazecode.personalfinance.core.domain.service.posession;

import cl.mazecode.personalfinance.core.domain.entity.PossessionEntity;
import cl.mazecode.personalfinance.core.domain.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.model.Possession;
import cl.mazecode.personalfinance.core.domain.repository.PossessionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PossessionServiceImpl implements PossessionService {
    @Autowired
    @NonNull
    private final PossessionRepository repository;

    @Override
    public Optional<Possession> find(Long id) throws NotFoundException {
        PossessionEntity possessionEntity = repository.findById(id)
                                                      .orElseThrow(NotFoundException::new);

        Possession possession = new Possession();
        BeanUtils.copyProperties(possession, possessionEntity);

        return Optional.of(possession);
    }

    @Override
    public Possession save(Possession possession) {
        PossessionEntity possessionEntity = new PossessionEntity();
        BeanUtils.copyProperties(possession, possessionEntity);

        this.repository.save(possessionEntity);

        return possession;
    }
}
