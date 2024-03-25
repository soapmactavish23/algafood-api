package com.hkprogrammer.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hkprogrammer.algafood.api.model.mixin.CidadeMixin;
import com.hkprogrammer.algafood.api.model.mixin.CozinhaMixin;
import com.hkprogrammer.algafood.domain.models.Cidade;
import com.hkprogrammer.algafood.domain.models.Cozinha;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() {
	    setMixInAnnotation(Cidade.class, CidadeMixin.class);
	    setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}

}
