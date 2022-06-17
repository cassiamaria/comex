package br.com.alura.comex.controller.dto;

import br.com.alura.comex.entity.Pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesPedidoDto {
    private LocalDate data;
    private BigDecimal valorTotal;
    private BigDecimal desconto;
    private List<ItemDePedidoDto> itens;

    private Long idCliente;

    private String NomeCliente;

    public DetalhesPedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotalPedido();
        this.desconto = pedido.getDesconto();
        this.itens = pedido.getItens().stream().map(ItemDePedidoDto::new).collect(Collectors.toList());
        this.idCliente = pedido.getCliente().getId();
        NomeCliente = pedido.getCliente().getNome();
    }
}
