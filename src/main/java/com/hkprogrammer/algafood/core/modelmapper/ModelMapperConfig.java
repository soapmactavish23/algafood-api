package com.hkprogrammer.algafood.core.modelmapper;

import com.hkprogrammer.algafood.api.v2.model.input.CidadeInputV2;
import com.hkprogrammer.algafood.domain.models.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hkprogrammer.algafood.api.v1.model.EnderecoModel;
import com.hkprogrammer.algafood.api.v1.model.input.ItemPedidoInput;
import com.hkprogrammer.algafood.domain.models.Endereco;
import com.hkprogrammer.algafood.domain.models.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();

		modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
				.addMappings(mapper -> mapper.skip(Cidade::setId));

		var enderecoToEnderecoModel = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

		enderecoToEnderecoModel.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
				.addMappings(mapper -> mapper.skip(ItemPedido::setId));

		return modelMapper;
	}

}
