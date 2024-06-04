package com.hkprogrammer.algafood.api.model;

import java.util.HashSet;
import java.util.Set;

import com.hkprogrammer.algafood.domain.models.Grupo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModel {

    private Long id;
    private String nome;
    private String email;
    private Set<Grupo> grupos = new HashSet<>();
    
    public boolean removerGrupo(Grupo grupo) {
        return getGrupos().remove(grupo);
    }

    public boolean adicionarGrupo(Grupo grupo) {
        return getGrupos().add(grupo);
    }
}