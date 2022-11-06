package cl.mazecode.personalfinance.core.domain.service.user;

import cl.mazecode.personalfinance.core.domain.entity.UserEntity;
import cl.mazecode.personalfinance.core.domain.exception.NotDeletedException;
import cl.mazecode.personalfinance.core.domain.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.model.User;
import cl.mazecode.personalfinance.core.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository;

    @Override
    public User save(User user) {
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
                      BeanUtils.copyProperties(user, userEntity);

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
}
