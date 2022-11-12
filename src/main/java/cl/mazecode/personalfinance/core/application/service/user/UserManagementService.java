package cl.mazecode.personalfinance.core.application.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementService {
//    private final FirebaseAuth firebaseAuth;
//
//    public void setTokenClaims(String uid, Map<EntityType, Map<Long, Set<Permission>>> requestedPermissions) throws FirebaseAuthException {
//        var claims = toUserClaims(requestedPermissions);
//
//        firebaseAuth.setCustomUserClaims(uid, claims);
//    }
//
//    private Map<String, Object> toUserClaims(Map<EntityType, Map<Long, Set<Permission>>> requestedPermissions) {
//        var customClaims = new ArrayList<String>();
//
//        requestedPermissions.forEach((entityType, entityTypePermissions) ->
//                entityTypePermissions.forEach((entityId, permissions) ->
//                        permissions.forEach(permission ->
//                                customClaims.add(generateClaim(entityType, entityId, permission))
//                        )));
//
//        return Map.of("custom_claims", customClaims);
//    }
//
//    private String generateClaim(EntityType entityType, Long entityId, Permission permission) {
//        return entityType +
//                ":" +
//                entityId +
//                ":" +
//                permission;
//    }
}
