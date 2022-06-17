package br.com.alura.comex.controller.form;

import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AtualizarProdutoForm {
    @NotNull
    @Size(min = 2)
    private String nome;

    private String descricao;

    @NotNull
    @Min(value = 0)
    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private Long categoriaId;

    public Produto atualizar(Long id, ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        Produto produto = produtoRepository.getReferenceById(id);
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPrecoUnitario(this.precoUnitario);
        produto.setQuantidadeEstoque(this.quantidadeEstoque);
        produto.setCategoria(categoriaRepository.findById(this.categoriaId).get());
        return produto;
    }
}