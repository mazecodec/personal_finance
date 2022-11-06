package cl.mazecode.personalfinance.presentation.api.controller;

import cl.mazecode.personalfinance.core.domain.exception.NotDeletedException;
import cl.mazecode.personalfinance.core.domain.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.model.User;
import cl.mazecode.personalfinance.core.domain.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping(path = "/users")
    public List<User> all() {
        return this.userService.all();
    }

    @PostMapping(path = "/users")
    public User save(@RequestBody User user) {
        return this.userService.save(user);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> find(@PathVariable(name = "id") Long id) throws NotFoundException {
        Optional<User> userOptional = this.userService.find(id);

        User user = userOptional.orElseThrow();

        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Map<String, Boolean>> remove(@PathVariable(name = "id") Long id) throws NotDeletedException, NotFoundException {
        boolean deleted = false;

        deleted = this.userService.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);

        return ResponseEntity.ok(response);
    }
}
