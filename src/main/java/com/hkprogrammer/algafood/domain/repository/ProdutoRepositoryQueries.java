package com.hkprogrammer.algafood.domain.repository;

import com.hkprogrammer.algafood.domain.models.FotoProduto;

public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);
	void delete(FotoProduto foto);
	
}
