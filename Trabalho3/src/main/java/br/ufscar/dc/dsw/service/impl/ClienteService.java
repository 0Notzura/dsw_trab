package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService {

	@Autowired
	IClienteDAO dao;

	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorCPF(String CPF) {
		return dao.findByCPF(CPF);
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorId(long id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	public boolean clienteTemLocacao(Long id) {
		return !dao.findById(id.longValue()).getLocacoes().isEmpty(); 
	}


}
