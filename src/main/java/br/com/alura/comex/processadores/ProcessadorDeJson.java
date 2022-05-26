package br.com.alura.comex.processadores;

import br.com.alura.comex.Pedido;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ProcessadorDeJson extends ProcessadorJackson{
    static final String arquivo = "pedidos.json";

    @Override
    public ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    @Override
    public String getArquivo() {
        return arquivo;
    }

}