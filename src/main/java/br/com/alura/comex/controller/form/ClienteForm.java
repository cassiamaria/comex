package br.com.alura.comex.controller.form;

import br.com.alura.comex.entity.Cliente;
import br.com.alura.comex.entity.Endereco;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteForm {
    @NotNull
    @Size(min = 2)
    private String nome;

    @NotNull
    @NotEmpty
    private Long cpf;

    @NotNull
    @NotEmpty
    private String telefone;

    @NotNull
    @NotEmpty
    private String rua;

    @NotNull
    @NotEmpty
    private String numero;

    private String complemento;

    @NotNull
    @NotEmpty
    private String bairro;

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    @NotEmpty
    @Size(max = 2)
    private String estado;

    public ClienteForm() {}

    public Cliente converter() {
        return new Cliente(nome, cpf, telefone, new Endereco(rua, numero, complemento, bairro, cidade, estado));
    }
}
