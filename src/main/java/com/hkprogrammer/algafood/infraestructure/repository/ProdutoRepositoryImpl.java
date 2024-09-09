package com.hkprogrammer.algafood.infraestructure.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hkprogrammer.algafood.domain.models.FotoProduto;
import com.hkprogrammer.algafood.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

	@Autowired
	private EntityManager manager;
	
	@Override
	public FotoProduto save(FotoProduto foto) {
		return manager.merge(foto);
	}

}
