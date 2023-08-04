package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Locadora;

public interface ILocadoraService {

	Locadora buscarPorCNPJ(String CNPJ);

	Locadora buscarPorId(long id);

	List<Locadora> buscarTodos();

	List<Locadora> buscarPorCidade(String cidade);

	List<Locadora> buscarPorLogado(String logado);


	boolean locadoraTemLocacao(Long id);
}
