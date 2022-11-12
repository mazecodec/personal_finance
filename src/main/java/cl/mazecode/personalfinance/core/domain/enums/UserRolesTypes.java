package cl.mazecode.personalfinance.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum UserRolesTypes {
    GUEST("ROLE_GUEST"), USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), ROOT("ROLE_ROOT");

    @NonNull
    private final String role;
}
