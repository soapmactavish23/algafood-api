package com.hkprogrammer.algafood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hkprogrammer.algafood.models.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	@Query(value = "SELECT * FROM cozinha WHERE id = ?1 LIMIT 1", nativeQuery = true)
	Cozinha buscar(Long id);
	
}
