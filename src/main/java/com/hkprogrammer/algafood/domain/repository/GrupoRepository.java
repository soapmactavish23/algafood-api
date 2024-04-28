package com.hkprogrammer.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hkprogrammer.algafood.domain.models.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}