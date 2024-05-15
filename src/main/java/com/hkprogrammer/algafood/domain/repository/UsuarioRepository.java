package com.hkprogrammer.algafood.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.hkprogrammer.algafood.domain.models.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	
}