package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;


public interface ILocacaoService {

	Locacao buscarPorId(Long id);

	List<Locacao> buscarTodos();

	List<Locacao> buscarPorCliente(Cliente cliente);

	List<Locacao> buscarPorLocadora(Locadora locadora);

	void salvar(Locacao locacao);

	void excluir(Long id);
}
