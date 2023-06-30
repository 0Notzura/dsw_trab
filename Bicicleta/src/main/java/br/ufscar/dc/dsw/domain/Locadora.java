package br.ufscar.dc.dsw.domain;

public class Locadora {

    private Long id;
    private String email;
    private String cnpj;
    private String cidade;
    private String senha;
    private String nome;


    public Locadora(Long id) {
        this.id = id;
    }

    public Locadora(String email, String cnpj, String cidade, String senha, String nome) {
        this.email = email;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.senha = senha;
        this.nome = nome;

    }

    public Locadora(Long id, String email, String cnpj, String cidade, String senha, String nome) {
        this(email, cnpj, cidade, senha, nome);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}