package br.com.alura.comex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class RelatorioSintetico {
    int totalDeProdutosVendidos;
    int totalDePedidosRealizados;
    long totalDeCategorias;
    BigDecimal montanteDeVendas = BigDecimal.ZERO;
    Pedido pedidoMaisBarato;
    Pedido pedidoMaisCaro;

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

    public void imprimirRelatorio() {
        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", getTotalDeCategorias());
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                        .format(getPedidoMaisBarato().getValorTotal().setScale(2, RoundingMode.HALF_DOWN)),
                getPedidoMaisBarato().getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                        .format(getPedidoMaisCaro().getValorTotal().setScale(2, RoundingMode.HALF_DOWN)),
                getPedidoMaisCaro().getProduto());
    }

    public void exibir() {
        imprimirRelatorio();
    }

}