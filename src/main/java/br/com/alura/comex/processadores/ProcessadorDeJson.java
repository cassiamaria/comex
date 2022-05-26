package br.com.alura.comex.processadores;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ProcessadorDeJson extends ProcessadorJackson{
    private static final String arquivo = "pedidos.json";

    @Override
    public ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    @Override
    public String getArquivo() {
        return arquivo;
    }

}