package br.com.alura.comex;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ProcessadorDeCsv processadorDeCsv = new ProcessadorDeCsv();
        List<Pedido> pedidos = processadorDeCsv.listarPedidos("pedidos.csv");
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);

        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioSintetico.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioSintetico.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioSintetico.getTotalDeCategorias());
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(relatorioSintetico.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(relatorioSintetico.getPedidoMaisBarato().getValorTotal().setScale(2, RoundingMode.HALF_DOWN)),
                relatorioSintetico.getPedidoMaisBarato().getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(relatorioSintetico.getPedidoMaisCaro().getValorTotal().setScale(2, RoundingMode.HALF_DOWN)),
                relatorioSintetico.getPedidoMaisCaro().getProduto());
    }
}
