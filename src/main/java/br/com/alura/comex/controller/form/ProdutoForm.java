package br.com.alura.comex.controller.form;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.repository.CategoriaRepository;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    private String descricao;

    @NotNull
    @Positive
    private BigDecimal precoUnitario;

    @NotNull
    private int quantidadeEstoque;

    @NotNull
    private Long categoriaId;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Produto converter(CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
        return new Produto(nome, descricao, precoUnitario, quantidadeEstoque, categoria.get());
    }
}
