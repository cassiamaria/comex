package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class RelatorioSintetico {
    int totalDeProdutosVendidos;
    int totalDePedidosRealizados;
    long totalDeCategorias;

    BigDecimal montanteDeVendas = BigDecimal.ZERO;

    Pedido pedidoMaisBarato;
    Pedido pedidoMaisCaro;

    HashSet<String> categoriasProcessadas = new HashSet<String>();

    public RelatorioSintetico(List<Pedido> listaDePedidos) {
        if (listaDePedidos == null || listaDePedidos.isEmpty())
            throw new IllegalArgumentException("A lista de pedidos está nula ou vazia!");

        this.totalDeProdutosVendidos = listaDePedidos.stream().mapToInt(Pedido::getQuantidade).sum();
        this.totalDePedidosRealizados = listaDePedidos.size();
        this.totalDeCategorias = listaDePedidos.stream().map(Pedido::getCategoria).distinct().count();
        this.montanteDeVendas = listaDePedidos.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.pedidoMaisBarato = listaDePedidos.stream()
                .min(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException("A lista não deve estar vazia!"));
        this.pedidoMaisCaro = listaDePedidos.stream()
                .max(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException("A lista não deve estar vazia!"));
    }

    public int getTotalDeProdutosVendidos() {
        return totalDeProdutosVendidos;
    }

    public int getTotalDePedidosRealizados() {
        return totalDePedidosRealizados;
    }

    public long getTotalDeCategorias() {
        return totalDeCategorias;
    }

    public BigDecimal getMontanteDeVendas() {
        return montanteDeVendas;
    }

    public Pedido getPedidoMaisBarato() {
        return pedidoMaisBarato;
    }

    public Pedido getPedidoMaisCaro() {
        return pedidoMaisCaro;
    }

    public HashSet<String> getCategoriasProcessadas() {
        return categoriasProcessadas;
    }
}