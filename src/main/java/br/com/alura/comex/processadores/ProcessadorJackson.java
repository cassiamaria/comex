package br.com.alura.comex.processadores;

import br.com.alura.comex.Pedido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public abstract class ProcessadorJackson implements Processador {
    public abstract ObjectMapper getMapper();
    public abstract String getArquivo();


    public List<Pedido> imprimir() throws URISyntaxException, IOException {
        URL recurso = ClassLoader.getSystemResource(getArquivo());
        FileReader reader = new FileReader(recurso.toURI().getPath());
        ObjectMapper objectMapper = getMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return getMapper().readValue(reader, new TypeReference<List<Pedido>>(){});
    }
}
