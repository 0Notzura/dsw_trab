package br.ufscar.dc.dsw.domain;

public class Cliente {

    private Long id;
    private String email;
    private String telefone;
    private String senha;
    private int cpf;
    private String sexo;
    private String nascimento;

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(String email, String telefone, String senha, String sexo,
            int cpf, String nascimento) {
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.sexo = sexo;
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    public Cliente(Long id, String email, String telefone, String senha, String sexo,
            int cpf, String nascimento) {
        this(email, telefone, senha, sexo, cpf, nascimento);
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return email + ", " + senha + "(" + cpf + ")";
    }
}
