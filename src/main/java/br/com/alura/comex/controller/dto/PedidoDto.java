package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Pedido;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoDto {

    private LocalDate data;
    private BigDecimal valorTotal;
    private BigDecimal desconto;
    private long quantidade;
    private Long idCliente;
    private String nomeCliente;

    public PedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotalPedido();
        this.desconto = pedido.getValorTotalDescontos();
        this.quantidade = pedido.getQuantidadeDeProdutos();
        this.idCliente = pedido.getCliente().getId();
        this.nomeCliente = pedido.getCliente().getNome();
    }

    public static Page<PedidoDto> converter(Page<Pedido> pedidos) {
        return pedidos.map(PedidoDto::new);
    }
}
