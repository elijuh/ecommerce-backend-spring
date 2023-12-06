package dev.elijuh.ecommerce.entities.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * @author elijuh
 */

@Getter
public enum Role {
    USER(new String[]{}),
    ADMIN(new String[]{"ITEM_CREATE", "ITEM_DELETE", "VIEW_USER_ORDERS"}, USER);

    private final Set<GrantedAuthority> authorities = new HashSet<>();
    private final Role[] inheritedRoles;

    Role(String[] authorities, Role... inheritedRoles) {
        this.authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));

        for (String authority : authorities) {
            this.authorities.add(new SimpleGrantedAuthority(authority));
        }

        Set<Role> inheriting = new HashSet<>();
        inheriting.add(this);
        for (Role role : inheritedRoles) {
            inheriting.add(role);
            inherit(role, inheriting);
        }

        this.inheritedRoles = inheritedRoles;
    }

    /*
        Recursive method for inheriting all parent
        roles without duplicating or getting caught
        in a bi-directional inheritance loop.
     */
    private void inherit(Role role, Set<Role> inheriting) {
        if (inheriting.contains(role)) return;

        this.authorities.addAll(role.authorities);
        for (Role inherited : role.inheritedRoles) {
            inherit(inherited, inheriting);
        }
    }
}
