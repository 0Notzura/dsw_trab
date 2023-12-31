package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "cliente/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarTodos());
		return "cliente/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {

		if (result.hasErrors()) {
			return "cliente/cadastro";
		}
		cliente.setPassword(encoder.encode(cliente.getPassword())); 
		usuarioService.salvar(cliente);
		attr.addFlashAttribute("sucess", "cliente.create.sucess");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", usuarioService.buscarPorId(id));
		return "cliente/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		System.out.println(cliente.getId());
		if (result.getFieldErrorCount() > 1 || result.getFieldError("CPF") == null) {
			System.out.println(result);
			return "cliente/cadastro";
		}	

		usuarioService.salvar(cliente);
		attr.addFlashAttribute("sucess", "cliente.edit.sucess");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (clienteService.clienteTemLocacao(id)) {
			attr.addFlashAttribute("fail", "cliente.delete.fail");
		}
		else{
		usuarioService.excluir(id);
		attr.addFlashAttribute("sucess", "cliente.delete.sucess");
		}	
		return "redirect:/clientes/listar";
	}


}
