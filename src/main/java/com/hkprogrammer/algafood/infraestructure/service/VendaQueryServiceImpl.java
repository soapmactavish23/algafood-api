package com.hkprogrammer.algafood.infraestructure.service;

import com.hkprogrammer.algafood.domain.filter.VendaDiariaFilter;
import com.hkprogrammer.algafood.domain.models.Pedido;
import com.hkprogrammer.algafood.domain.models.dto.VendaDiaria;
import com.hkprogrammer.algafood.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var functionDateDataCriacao = builder.function("date", Date.class, root.get("dataCriacao"));

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao, builder.count(root.get("id")), builder.sum(root.get("valorTotal")));

        query.select(selection);
        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }
}
