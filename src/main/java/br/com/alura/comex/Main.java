package br.com.alura.comex;

import br.com.alura.comex.menu.MenuDeRelatorios;
import br.com.alura.comex.processadores.ProcessadorDeCsv;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Pedido> pedidos = new ProcessadorDeCsv().imprimir();
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);

        MenuDeRelatorios menuDeRelatorios = new MenuDeRelatorios();
        menuDeRelatorios.exibirRelatorio();

    }
}