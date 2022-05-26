package br.com.alura.comex.enums;

import br.com.alura.comex.processadores.Processador;
import br.com.alura.comex.processadores.ProcessadorDeCsv;
import br.com.alura.comex.processadores.ProcessadorDeJson;
import br.com.alura.comex.processadores.ProcessadorDeXml;

public enum TipoProcessador {
    CSV(new ProcessadorDeCsv()),
    JSON(new ProcessadorDeJson()),
    XML(new ProcessadorDeXml());

    private final Processador processador;
    TipoProcessador(Processador processador) {
        this.processador = processador;
    }
    public Processador getProcessador() {
        return processador;
    }
}
