package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCNPJ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(value = {"locacoes"})
@Entity
@Table(name = "Locadora")
public class Locadora extends Usuario {

	@UniqueCNPJ (message = "{Unique.locadora.CNPJ}")
	@NotBlank
	@Size(min = 18, max = 18, message = "{Size.locadora.CNPJ}")
	@Column(nullable = true, unique = true, length = 60)
	private String CNPJ;
	
	@NotBlank
	@Size(min = 3, max = 60)
	@Column(nullable = true, unique = false, length = 60)
	private String name;

	@NotBlank
	@Size(min = 3, max = 40)
	@Column(nullable = true, unique = false, length = 60)
	private String cidade;

	@OneToMany(mappedBy = "locadora")
	private List<Locacao> locacoes;
	
	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}
}
