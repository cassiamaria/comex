package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.alura.comex.Pedido;
import br.com.alura.comex.relatorios.RelatorioProdutosMaisVendidos.ProdutosMaisVendidos;

class RelatorioProdutosMaisVendidosTest {
    List<Pedido> pedidos = new ArrayList<>();
    RelatorioProdutosMaisVendidos relatorio = new RelatorioProdutosMaisVendidos();

    private Consumer imprimirRelatorio() {
        Consumer consumer = Mockito.mock(Consumer.class);
        relatorio.imprimirRelatorio(pedidos, consumer);
        return consumer;
    }

    @Test
    void gerarRelatorioComOsDoisPedidosMaisVendidos() {
        pedidos.add(new Pedido("JOGOS", "Playstation 5", "Cassia", new BigDecimal("4550.00"), 2,
                LocalDate.of(2022, 6, 1)));
        pedidos.add(new Pedido("LIVROS", "Game of Thrones", "Maria", new BigDecimal("25.00"), 4,
                LocalDate.of(2022, 6, 1)));
        pedidos.add(new Pedido("MANGÁS", "One piece", "Cassia", new BigDecimal("40.00"), 10,
                LocalDate.of(2022, 6, 1)));

        Consumer consumer = imprimirRelatorio();
        List<ProdutosMaisVendidos> resultado = relatorio.getProdutosMaisVendidos();

        Assertions.assertEquals("Game of Thrones", resultado.get(1).getProduto());
        Assertions.assertEquals("One piece", resultado.get(2).getProduto());
        Assertions.assertEquals(4, resultado.get(1).getQuantidadeVendida());
        Assertions.assertEquals(10, resultado.get(2).getQuantidadeVendida());

        Mockito.verify(consumer, Mockito.times(3)).accept(Mockito.any());
    }

    @Test
    void aoGerarUmRelatorioComUmaListaVaziaDeveRetornarUmaException() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> relatorio.imprimirRelatorio(pedidos, null));
    }
}