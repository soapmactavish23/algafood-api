package com.hkprogrammer.algafood.core.security.authorizationserver;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String fullName;

    public AuthUser(com.hkprogrammer.algafood.domain.models.Usuario usuario, Collection<GrantedAuthority> authorities) {
        super(usuario.getEmail(), usuario.getSenha(), authorities);

        this.userId = usuario.getId();
        this.fullName = usuario.getEmail();

    }

}
