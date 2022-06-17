package br.com.alura.comex.controller.dto.projecao;

import java.math.BigDecimal;

public interface PedidosPorCategoriaProjecao {
    String getNome();
    Long getQuantidadeProdutos();
    BigDecimal getMontanteVendido();

}
