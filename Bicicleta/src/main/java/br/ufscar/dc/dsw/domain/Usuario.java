package br.ufscar.dc.dsw.domain;

public class Usuario {

	private String nome;
	private String senha;
	private String papel;
	private String email;
    private String telefone;
    private int cpf;
    private String sexo;
    private String nascimento;

	public Usuario(int cpf) {
		this.cpf = cpf;
	}

	public Usuario(String nome, String senha, String papel,String email, String telefone, String sexo,
            int cpf, String nascimento) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.papel = papel;
		this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.sexo = sexo;
        this.cpf = cpf;
        this.nascimento = nascimento;
	}

	public Usuario(int cpf, String nome, String senha, String papel) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.papel = papel;
		
	}

	public int getId() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
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
}
