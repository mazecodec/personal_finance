package cl.mazecode.personalfinance.presentation.api.controller;

import cl.mazecode.personalfinance.core.application.exception.EmailExistsException;
import cl.mazecode.personalfinance.core.application.exception.NotDeletedException;
import cl.mazecode.personalfinance.core.application.exception.NotFoundException;
import cl.mazecode.personalfinance.core.application.service.account.AccountService;
import cl.mazecode.personalfinance.core.domain.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/accounts")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @PostMapping(path = "")
    public ResponseEntity<Account> save(@RequestBody Account account, UriComponentsBuilder b) throws URISyntaxException, EmailExistsException {
        Account newAccount = this.accountService.save(account);

        UriComponents uriComponents = b.path("/api/v1/accounts/{id}")
                                       .buildAndExpand(newAccount.getId());

        return ResponseEntity.created(uriComponents.toUri())
                             .body(newAccount);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Account> find(@PathVariable(name = "id") Long id) throws NotFoundException {
        Optional<Account> accountOptional = this.accountService.find(id);

        Account account = accountOptional.orElseThrow();

        return ResponseEntity.ok(account);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, Boolean>> remove(@PathVariable(name = "id") Long id) throws NotDeletedException, NotFoundException {
        boolean deleted = false;

        deleted = this.accountService.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);

        return ResponseEntity.ok(response);
    }
}
