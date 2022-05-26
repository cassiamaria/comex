package br.com.alura.comex.processadores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ProcessadorDeXml extends ProcessadorJackson{
    static final String arquivo = "pedidos.xml";

    @Override
    public ObjectMapper getMapper() {
        return new XmlMapper();
    }

    @Override
    public String getArquivo() {
        return arquivo;
    }
}