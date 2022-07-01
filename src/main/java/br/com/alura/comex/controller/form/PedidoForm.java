package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import br.com.alura.comex.utils.Descontos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class PedidoForm {
    @NotNull
    @Min(0)
    private Long idCliente;

    @NotNull
    private List<ItemDePedidoForm> itens;

    public PedidoForm() {}

    public Pedido converter(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        Optional<Cliente> cliente = clienteRepository.findById(this.idCliente);
        Pedido pedido = new Pedido(cliente.get());
        cliente.get().adicionarPedido(pedido);

        this.itens.stream().forEach(pedidos -> {
            pedido.adicionarItem(pedidos.converter(produtoRepository));
        });

        Descontos.aplicarDesconto(pedido);
        return pedido;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public List<ItemDePedidoForm> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "PedidoFrom{" +
                "idCliente=" + idCliente +
                ", itens=" + itens +
                '}';
    }
}
