package com.hkprogrammer.algafood.api.v2.assembler;

import com.hkprogrammer.algafood.api.v1.model.input.CidadeInput;
import com.hkprogrammer.algafood.api.v2.model.input.CidadeInputV2;
import com.hkprogrammer.algafood.domain.models.Cidade;
import com.hkprogrammer.algafood.domain.models.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("cidadeInputDisassemblerV2")
public class CidadeInputDisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInputV2 cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInputV2 cidadeInput, Cidade cidade) {
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeInput, cidade);
    }

}