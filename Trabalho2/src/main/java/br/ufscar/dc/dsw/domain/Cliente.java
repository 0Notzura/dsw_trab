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

	@NotBlank
	@Size(min = 14, max = 14)
	@Column(nullable = true, unique = true, length = 60)
	private String CPF;

	@NotBlank(message = "{NotBlank.cliente.autor}")
	@Size(max = 60)
	@Column(nullable = true, length = 60)
	private String name;
    
	@NotBlank
	@Size(min = 11, max = 15)
	@Column(nullable = true, unique = false, length = 60)
	private String phone;
	
    @NotBlank
	@Size(min = 1, max = 60)
	@Column(nullable = true, unique = false, length = 60)
	private String gender;
    
	@NotNull(message = "{NotNull.cliente.editora}")
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

	public void setPhone(String phone) {
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