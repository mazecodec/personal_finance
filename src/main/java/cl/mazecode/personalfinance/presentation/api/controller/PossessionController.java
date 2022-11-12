package cl.mazecode.personalfinance.presentation.api.controller;


import cl.mazecode.personalfinance.core.application.service.security.LocalPermissionService;
import cl.mazecode.personalfinance.core.domain.exception.NotFoundException;
import cl.mazecode.personalfinance.core.domain.model.Possession;
import cl.mazecode.personalfinance.core.domain.service.posession.PossessionService;
import cl.mazecode.personalfinance.core.domain.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/possessions")
@Log4j2
public class PossessionController {
    @Autowired
    private PossessionService possessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocalPermissionService permissionService;

    @GetMapping(value = "/{id}")
    @ResponseBody
    @PostAuthorize("hasPermission(returnObject, 'READ') or hasPermission(returnObject, 'ADMINISTRATION')")
    public Possession findOne(@PathVariable("id") final Long id) throws NotFoundException {
        return possessionService.find(id)
                                .orElseThrow();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Possession> create(@Valid Possession possession, Authentication authentication, UriComponentsBuilder b) {
        possession.setOwner(userService.findByEmail(authentication.getName()));

        Possession newPossession = possessionService.save(possession);

        log.info(newPossession);

        // permissionService.addPermissionForAuthority(possession, BasePermission.ADMINISTRATION, "ADMIN");
        permissionService.addPermissionForUser(newPossession, BasePermission.ADMINISTRATION, authentication.getName());

        UriComponents uriComponents = b.path("/api/v1/users/{id}")
                                       .buildAndExpand(newPossession.getId());

        return ResponseEntity.created(uriComponents.toUri())
                             .body(newPossession);
    }
}
