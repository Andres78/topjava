package es.ahs.mealscounter.model;

import org.springframework.security.core.GrantedAuthority;

/**
 *  Andrey Kuznetsov
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
