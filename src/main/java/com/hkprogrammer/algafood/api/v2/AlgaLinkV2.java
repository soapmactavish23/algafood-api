package com.hkprogrammer.algafood.api.v2;

import com.hkprogrammer.algafood.api.v1.controller.*;
import com.hkprogrammer.algafood.api.v2.controller.CidadeControllerV2;
import com.hkprogrammer.algafood.api.v2.controller.CozinhaControllerV2;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class AlgaLinkV2 {

    public Link linkToCidades(String rel) {
        return WebMvcLinkBuilder.linkTo(CidadeControllerV2.class).withRel(rel);
    }

    public Link linkToCidades() {
        return linkToCidades(IanaLinkRelations.SELF.value());
    }

    public Link linkToCozinhas(String rel) {
        return WebMvcLinkBuilder.linkTo(CozinhaControllerV2.class).withRel(rel);
    }

    public Link linkToCozinhas() {
        return linkToCozinhas(IanaLinkRelations.SELF.value());
    }

}
