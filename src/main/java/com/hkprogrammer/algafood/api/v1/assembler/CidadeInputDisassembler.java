package com.hkprogrammer.algafood.api.v1.assembler;

import com.hkprogrammer.algafood.api.v1.model.input.CidadeInput;
import com.hkprogrammer.algafood.domain.models.Cidade;
import com.hkprogrammer.algafood.domain.models.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("cidadeInputDisassemblerV1")
public class CidadeInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeInput, cidade);
    }

}