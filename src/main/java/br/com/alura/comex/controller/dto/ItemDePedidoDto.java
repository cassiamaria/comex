package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.ItemDePedido;

import java.math.BigDecimal;

public class ItemDePedidoDto {
    private Long id;
    private BigDecimal precoUnitario;
    private Integer quantidade;
    private String produto;
    private String categoria;
    private BigDecimal desconto;
    private BigDecimal valorFinal;

    public ItemDePedidoDto(ItemDePedido item) {
        this.id = item.getId();
        this.precoUnitario = item.getPrecoUnitario();
        this.quantidade = item.getQuantidade();
        this.produto = item.getProdutoId().getNome();
        this.categoria = item.getProdutoId().getCategoria().getNome();
        this.desconto = item.getDesconto();
        this.valorFinal = item.getValorTotalItem();
    }
}
