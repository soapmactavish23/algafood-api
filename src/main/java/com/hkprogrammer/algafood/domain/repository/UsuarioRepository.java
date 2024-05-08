package com.hkprogrammer.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.hkprogrammer.algafood.domain.models.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}