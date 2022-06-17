package br.com.alura.comex.controller.form;

import br.com.alura.comex.entity.ItemDePedido;
import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ItemDePedidoForm {
    @NotNull
    @Min(0)
    @Valid
    private Long idProduto;

    @Min(1)
    private int quantidadeProduto;

    public ItemDePedidoForm() {}

    private Produto validar(ProdutoRepository produtoRepository){
        Optional<Produto> produto = produtoRepository.findById(this.idProduto);
        if (produto.get().getQuantidadeEstoque() < this.quantidadeProduto){
            throw new RuntimeException("O produto não tem estoque!");
        }

        produto.get().setQuantidadeEstoque(produto.get().getQuantidadeEstoque() - this.quantidadeProduto);
        return produto.get();
    }

    public ItemDePedido converter(ProdutoRepository produtoRepository){
        return new ItemDePedido(this.quantidadeProduto, validar(produtoRepository));
    }
}
