package cl.mazecode.personalfinance.core.application.service.user;

import cl.mazecode.personalfinance.core.application.exception.EmailExistsException;
import cl.mazecode.personalfinance.core.application.exception.NotDeletedException;
import cl.mazecode.personalfinance.core.application.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user) throws EmailExistsException;

    List<User> all();

    Optional<User> find(Long id) throws NotFoundException;

    boolean delete(Long id) throws NotFoundException, NotDeletedException;

    User findByEmail(String email);
}
