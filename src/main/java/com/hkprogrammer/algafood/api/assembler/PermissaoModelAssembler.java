package com.hkprogrammer.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.api.model.PermissaoModel;
import com.hkprogrammer.algafood.domain.models.Permissao;
import java.util.Collection;

@Component
public class PermissaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public PermissaoModel toModel(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoModel.class);
    }
    
    public List<PermissaoModel> toCollectionModel(Collection<Permissao> permissoes) {
        return permissoes.stream()
                .map(permissao -> toModel(permissao))
                .collect(Collectors.toList());
    }
    
}