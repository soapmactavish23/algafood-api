package com.hkprogrammer.algafood.domain.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.hkprogrammer.algafood.domain.exception.NegocioException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	@Embedded
	private Endereco enderecoEntrega;

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;

	@CreationTimestamp
	private OffsetDateTime dataCriacao;

	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public void calcularValorTotal() {
		getItens().forEach(ItemPedido::calcularPrecoTotal);

		this.subtotal = getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);

		this.valorTotal = this.subtotal.add(this.taxaFrete);
	}

	public void definirFrete() {
		setTaxaFrete(getRestaurante().getTaxaFrete());
	}

	public void atribuirPedidoAosItens() {
		getItens().forEach(item -> item.setPedido(this));
	}

	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
	}

	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());
	}

	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataConfirmacao(OffsetDateTime.now());
	}

	private void setStatus(StatusPedido novoStatus) {
		if (getStatus().naoPodeAlterarPara(novoStatus)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
					getId(), getStatus().getDescricao(), StatusPedido.CANCELADO.getDescricao()));
		}
		
		this.status = novoStatus;
	}

}