package com.hkprogrammer.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {
    private String logradouro;
    private String cep;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoModel cidade;
}
