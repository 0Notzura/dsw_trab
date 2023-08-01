package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Locacao")
public class Locacao extends AbstractEntity<Long> {
    
	@ManyToOne
    @JoinColumn(name = "locadora_id")
	private Locadora locadora;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@NotBlank
	@Size(min = 8, max = 12)
	@Column(nullable = false, unique = false, length = 60)
	private String dataRes;

	public String getDataRes() {
		return dataRes;
	}

	public void setDataRes(String dataRes) {
		this.dataRes = dataRes;
	}
	
	public int getHourRes() {
		return hourRes;
	}

	public void setHourRes(int hourRes) {
		this.hourRes = hourRes;
	}

	@Column(nullable = false, unique = false)
	private int hourRes;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

    public Locadora getLocadora() {
		return locadora;
	}

	public void setLocadora(Locadora locadora) {
		this.locadora = locadora;
	}

}
