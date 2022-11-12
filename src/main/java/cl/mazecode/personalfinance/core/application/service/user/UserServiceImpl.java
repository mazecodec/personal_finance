package cl.mazecode.personalfinance.core.application.service.user;

import cl.mazecode.personalfinance.core.application.exception.EmailExistsException;
import cl.mazecode.personalfinance.core.application.exception.NotDeletedException;
import cl.mazecode.personalfinance.core.application.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.entity.UserEntity;
import cl.mazecode.personalfinance.core.domain.model.User;
import cl.mazecode.personalfinance.infrastucture.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    @NonNull
    private final UserRepository repository;

    @Override
    public User save(User user) throws EmailExistsException {
        if (this.emailExist(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        this.repository.save(userEntity);

        return user;
    }

    @Override
    public List<User> all() {
        List<UserEntity> all = this.repository.findAll();

        return all.stream()
                  .map(userEntity -> {
                      User user = new User();
                      BeanUtils.copyProperties(userEntity, user);

                      return user;
                  })
                  .collect(Collectors.toList());
    }

    @Override
    public Optional<User> find(Long id) throws NotFoundException {
        UserEntity userEntity = this.repository.findById(id)
                                               .orElseThrow(NotFoundException::new);
        User user = new User();
        BeanUtils.copyProperties(user, userEntity);

        return Optional.of(user);
    }

    @Override
    public boolean delete(Long id) throws NotFoundException, NotDeletedException {
        UserEntity userEntity = this.repository.findById(id)
                                               .orElseThrow(NotFoundException::new);

        try {
            this.repository.delete(userEntity);
        } catch (Exception e) {
            throw new NotDeletedException();
        }

        return true;
    }

    @Override
    public User findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    private boolean emailExist(String email) {
        final User user = this.repository.findByEmail(email);

        return user != null;
    }

}
