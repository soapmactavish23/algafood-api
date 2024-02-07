package com.hkprogrammer.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hkprogrammer.algafood.domain.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {	
	
}
