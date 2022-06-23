package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.enuns.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;

public class AtualizacaoCategoriaForm {
    private String nome;
    private StatusCategoria status = StatusCategoria.ATIVA;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public void setStatus(StatusCategoria status) {
        this.status = status;
    }

    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.setNome(this.nome);
        return categoria;
    }
}
