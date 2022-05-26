package br.com.alura.comex.menu;

import br.com.alura.comex.Pedido;
import br.com.alura.comex.RelatorioSintetico;
import br.com.alura.comex.enums.TipoProcessador;
import br.com.alura.comex.processadores.Processador;

import java.util.List;
import java.util.Scanner;

public class MenuDeRelatorios {
    Scanner entrada = new Scanner(System.in);

    public void imprimirProcessador() {
        System.out.println("Informe qual o tipo do arquivo você deseja imprimir (utilize letras maiúsculas): ");
        System.out.println("CSV");
        System.out.println("JSON");
        System.out.println("XML");
    }

    private void relatorio(List<Pedido> listaDePedidos) {
        RelatorioSintetico relatorio = new RelatorioSintetico(listaDePedidos);
        relatorio.exibir();
    }

    private List<Pedido> arquivo() throws Exception {
        imprimirProcessador();
        String opcao = entrada.nextLine();
        TipoProcessador tipoProcessador = TipoProcessador.valueOf(opcao);
        Processador processador = tipoProcessador.getProcessador();
        return processador.imprimir();
    }

    public void exibirRelatorio() throws Exception {
        List<Pedido> listaDePedidos = arquivo();
        relatorio(listaDePedidos);
    }
}
