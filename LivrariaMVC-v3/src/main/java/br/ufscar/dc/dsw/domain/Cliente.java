package br.ufscar.dc.dsw.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario {

	@NotBlank(message = "{NotBlank.cliente.titulo}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String CPF;

	@NotBlank(message = "{NotBlank.cliente.autor}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String name;
    
	@NotNull(message = "{NotNull.cliente.ano}")
	@Column(nullable = false, length = 5)
	private String phone;
	
	@NotNull(message = "{NotNull.cliente.preco}")
	@Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private String gender;
    
	@NotNull(message = "{NotNull.cliente.editora}")
	@ManyToOne
	@JoinColumn(name = "editora_id")
	private String dataNascimento;

	@OneToMany(mappedBy = "cliente")
	private List<Locacao> locacoes;
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getPhone() {
		return phone;
	}

	public void setPhone(String Phone) {
		this.phone = phone;
	}

    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

    public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}

}