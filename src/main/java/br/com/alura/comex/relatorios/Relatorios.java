package br.com.alura.comex.relatorios;

import br.com.alura.comex.Pedido;

import java.util.List;
import java.util.function.Consumer;

public abstract class Relatorios {
    public void imprimirRelatorio(List<Pedido> pedidos, Consumer<String> impressoraDeRelatorio) {
        if (pedidos == null || pedidos.isEmpty())
            throw new IllegalArgumentException("A lista não pode estar vazia!");

    }
}
