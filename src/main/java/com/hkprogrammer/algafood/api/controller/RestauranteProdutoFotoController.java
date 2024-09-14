package com.hkprogrammer.algafood.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hkprogrammer.algafood.api.assembler.FotoProdutoModelAssembler;
import com.hkprogrammer.algafood.api.model.FotoProdutoModel;
import com.hkprogrammer.algafood.api.model.input.FotoProdutoInput;
import com.hkprogrammer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.hkprogrammer.algafood.domain.models.FotoProduto;
import com.hkprogrammer.algafood.domain.models.Produto;
import com.hkprogrammer.algafood.domain.service.CadastroProdutoService;
import com.hkprogrammer.algafood.domain.service.CatalogoFotoProdutoService;
import com.hkprogrammer.algafood.domain.service.FotoStorageService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

       Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
       
       MultipartFile arquivo = fotoProdutoInput.getArquivo();
       
       FotoProduto foto = new FotoProduto();
       foto.setProduto(produto);
       foto.setDescricao(fotoProdutoInput.getDescricao());
       foto.setContentType(arquivo.getContentType());
       foto.setTamanho(arquivo.getSize());
       foto.setNomeArquivo(arquivo.getOriginalFilename());
       
       FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());
       
       return fotoProdutoModelAssembler.toModel(fotoSalva);

    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
        return fotoProdutoModelAssembler.toModel(fotoProduto);
    }
    
    @GetMapping
    public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId, 
    		@PathVariable Long produtoId, @RequestHeader("accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
    	try {
    		FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
        	
    		MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
    		
    		List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
    		
    		verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
    		
        	InputStream inputStream = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
        	
        	return ResponseEntity.ok()
        			.contentType(MediaType.IMAGE_JPEG)
        			.body(new InputStreamResource(inputStream));
        	
    	} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
    	
    }
    
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId, 
            @PathVariable Long produtoId) {
        catalogoFotoProduto.excluir(restauranteId, produtoId);
    }   

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, 
			List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		
		boolean compativel = mediaTypesAceitas
				.stream()
				.anyMatch(mediaTypesAceita -> mediaTypesAceita.isCompatibleWith(mediaTypeFoto));
		
		if(!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
		
	}
    
}
