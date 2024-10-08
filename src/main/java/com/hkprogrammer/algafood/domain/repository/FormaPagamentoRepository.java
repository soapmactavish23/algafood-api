package com.hkprogrammer.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hkprogrammer.algafood.domain.models.FormaPagamento;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    @Query("select max(dataAtualizacao) from FormaPagamento")
    OffsetDateTime getDataUltimaAtualizacao();

    @Query("select dataAtualizacao from FormaPagamento where id = :formaPagamentoId")
    OffsetDateTime getDataAtualizacaoById(Long formaPagamentoId);


}
