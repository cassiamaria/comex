package br.com.alura.comex.processadores;

import br.com.alura.comex.Pedido;

import java.util.List;

public interface Processador {
    List<Pedido> processa() throws Exception;
}