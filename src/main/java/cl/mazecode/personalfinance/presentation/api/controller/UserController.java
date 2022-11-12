package cl.mazecode.personalfinance.presentation.api.controller;

import cl.mazecode.personalfinance.core.domain.exception.EmailExistsException;
import cl.mazecode.personalfinance.core.domain.exception.NotDeletedException;
import cl.mazecode.personalfinance.core.domain.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.model.User;
import cl.mazecode.personalfinance.core.domain.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    //    @PreAuthorize("hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN')")
    @GetMapping(path = "")
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(this.userService.all());
    }

    @PostMapping(path = "")
    public ResponseEntity<User> save(@RequestBody User user, UriComponentsBuilder b) throws URISyntaxException, EmailExistsException {
        User newUser = this.userService.save(user);

        UriComponents uriComponents = b.path("/api/v1/users/{id}")
                                       .buildAndExpand(newUser.getId());

        return ResponseEntity.created(uriComponents.toUri())
                             .body(newUser);
    }

    //    @PreAuthorize("hasRole('ADMIN') or principal.userId == #id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> find(@PathVariable(name = "id") Long id) throws NotFoundException {
        Optional<User> userOptional = this.userService.find(id);

        User user = userOptional.orElseThrow();

        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, Boolean>> remove(@PathVariable(name = "id") Long id) throws NotDeletedException, NotFoundException {
        boolean deleted = false;

        deleted = this.userService.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);

        return ResponseEntity.ok(response);
    }
}
