package com.hkprogrammer.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.domain.exception.FotoProdutoNaoEncontradaException;
import com.hkprogrammer.algafood.domain.models.FotoProduto;
import com.hkprogrammer.algafood.domain.repository.ProdutoRepository;
import com.hkprogrammer.algafood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
	    return produtoRepository.findFotoById(restauranteId, produtoId)
	            .orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
	}       
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
	
		
		if(fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.inputStream(dadosArquivo)
				.build();
		
		if(nomeArquivoExistente != null) {
			fotoStorage.remover(nomeArquivoExistente);	
		}
		
		fotoStorage.substituir(nomeArquivoExistente, novaFoto);
		
		return foto; 
	}
	
}
