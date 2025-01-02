package com.hkprogrammer.algafood.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    public @interface Cozinhas {

        @Target(ElementType.METHOD)
        @PreAuthorize("isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        public @interface PodeConsultar {
        }

        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
        public @interface PodeEditar {
        }

    }

}
