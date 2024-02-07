package com.hkprogrammer.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hkprogrammer.algafood.domain.models.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
}
