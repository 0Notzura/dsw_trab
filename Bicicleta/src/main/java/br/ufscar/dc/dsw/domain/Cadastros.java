package br.ufscar.dc.dsw.domain;

public class Cadastros {

	private Long id;
	private String dia;
	private String hora;
	private Locadora locadora;
	private Usuario usuario;

	public Cadastros(Long id, String dia, String hora, Locadora locadora, Usuario usuario) {
		this.id = id;
		this.dia = dia;
		this.hora = hora;
		this.locadora = locadora;
		this.usuario = usuario;
	}

	public Cadastros(String dia, String hora,  Locadora locadora, Usuario usuario) {
		super();
		this.dia = dia;
		this.hora = hora;
		this.locadora = locadora;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Locadora getLocadora() {
		return locadora;
	}

	public void setLocadora(Locadora locadora) {
		this.locadora = locadora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
