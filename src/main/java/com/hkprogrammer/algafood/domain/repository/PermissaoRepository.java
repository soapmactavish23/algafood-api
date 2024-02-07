package com.hkprogrammer.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hkprogrammer.algafood.domain.models.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
}
