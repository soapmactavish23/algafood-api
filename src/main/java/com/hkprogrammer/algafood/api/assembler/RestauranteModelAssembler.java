package com.hkprogrammer.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.hkprogrammer.algafood.api.AlgaLink;
import com.hkprogrammer.algafood.api.controller.RestauranteController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.api.model.RestauranteModel;
import com.hkprogrammer.algafood.domain.models.Restaurante;

@Component
public class RestauranteModelAssembler
		extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLink algaLinks;

	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, restauranteModel);

		restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));

		restauranteModel.getCozinha().add(
				algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

		restauranteModel.getEndereco().getCidade().add(
				algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));

		restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(),
				"formas-pagamento"));

//		restauranteModel.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(),
//				"responsaveis"));

		return restauranteModel;
	}

	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities)
				.add(algaLinks.linkToRestaurantes());
	}
}
