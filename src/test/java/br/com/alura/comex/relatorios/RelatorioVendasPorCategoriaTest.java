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
import br.com.alura.comex.relatorios.RelatorioVendasPorCategoria.VendasCategoria;

class RelatorioVendasCategoriaTest {
    List<Pedido> pedidos = new ArrayList<>();
    RelatorioVendasPorCategoria relatorioVendasCategoria = new RelatorioVendasPorCategoria();
    private Consumer iniciarTeste() {
        Consumer consumer = Mockito.mock(Consumer.class);
        relatorioVendasCategoria.imprimirRelatorio(pedidos, consumer);
        return consumer;
    }

    @Test
    void gerarRelatorioComApenasUmPedido() {
        Pedido pedido = new Pedido(
                "JOGOS", "The Last Of Us 2", "Cassia", new BigDecimal("100.00"), 1,
                LocalDate.of(2022, 6, 1)
        );

        pedidos = List.of(pedido);
        Consumer consumer = iniciarTeste();

        List<VendasCategoria> resultado = relatorioVendasCategoria.getVendasPorCategoria();
        Assertions.assertEquals(1, resultado.get(0).getQuantidadeVendida());
        Assertions.assertEquals(new BigDecimal("100.00"), resultado.get(0).getMontante());
        Assertions.assertEquals("JOGOS", resultado.get(0).getCategoria());

        Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());
    }

    @Test
    void gerarRelatorioComMaisDeUmPedido() {
        pedidos.add(new Pedido("LIVROS", "Game Of Thrones", "Cassia", new BigDecimal("199.00"), 1,
                LocalDate.of(2022, 6, 1)));
        pedidos.add(new Pedido("MANGÁS", "One Piece VOL.1", "Cassia", new BigDecimal("40.00"), 1,
                LocalDate.of(2022, 6, 1)));
        pedidos.add(new Pedido("JOGOS", "The Last Of Us 2", "Maria", new BigDecimal("100.00"), 2,
                LocalDate.of(2022, 6, 2)));
        pedidos.add(new Pedido("GAMER", "Mouse Redragon", "Maria", new BigDecimal("199.00"), 1,
                LocalDate.of(2022, 6, 2)));

        Consumer consumer = iniciarTeste();
        List<VendasCategoria> resultado = relatorioVendasCategoria.getVendasPorCategoria();

        Assertions.assertEquals("GAMER", resultado.get(0).getCategoria());
        Assertions.assertEquals("JOGOS", resultado.get(1).getCategoria());
        Assertions.assertEquals(2, resultado.get(1).getQuantidadeVendida());
        Assertions.assertEquals(new BigDecimal("200.00"), resultado.get(1).getMontante());

        Mockito.verify(consumer, Mockito.times(4)).accept(Mockito.any());
    }

    @Test
    void aoGerarUmRelatorioComUmaListaVaziaDeveRetornarUmaException() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> relatorioVendasCategoria.imprimirRelatorio(pedidos, null));
    }
}