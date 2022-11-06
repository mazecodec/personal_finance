package cl.mazecode.personalfinance.core.domain.service.user;

import cl.mazecode.personalfinance.core.domain.exception.NotDeletedException;
import cl.mazecode.personalfinance.core.domain.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    List<User> all();

    Optional<User> find(Long id) throws NotFoundException;

    boolean delete(Long id) throws NotFoundException, NotDeletedException;
}
