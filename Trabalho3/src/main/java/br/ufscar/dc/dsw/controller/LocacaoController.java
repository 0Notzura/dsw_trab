package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {
	@Autowired
	private ILocacaoService locacaoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private ILocadoraService locadoraService;
	
	private Cliente getClienteAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
		return clienteService.buscarPorId(user.getId());
	}
	
	private boolean verificaDataHoraOcupada(Locacao locacao) {
		List<Locacao> locacoes = locacaoService.buscarTodos();
	
		for (Locacao l : locacoes) {
			if (l.getDataRes().equals(locacao.getDataRes()) && 
				l.getHourRes() == locacao.getHourRes()) {
				return true;
			}
		}
	
		return false;
	}
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Locacao locacao, ModelMap model) {
		model.addAttribute("locadoras", locadoraService.buscarTodos());
		return "locacao/cadastro";
	}

	@GetMapping("/listar")
public String listar(@RequestParam(required = false) String c, ModelMap model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UsuarioDetails userDetails = (UsuarioDetails) authentication.getPrincipal();

    if (userDetails.hasRole("ROLE_CLIENTE")) {
        Cliente clienteDoUsuario = clienteService.buscarPorId(userDetails.getId());
        List<Locacao> locacoes = locacaoService.buscarPorCliente(clienteDoUsuario);
        model.addAttribute("locacoes", locacoes);
    } else if (userDetails.hasRole("ROLE_LOCADORA")) {
        Locadora locadoraDoUsuario = locadoraService.buscarPorId(userDetails.getId());
        List<Locacao> locacoes = locacaoService.buscarPorLocadora(locadoraDoUsuario);
        model.addAttribute("locacoes", locacoes);
    }

    return "locacao/lista";
}


	@PostMapping("/salvar")
	public String salvar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("locadoras", locadoraService.buscarTodos());
			return "locacao/cadastro";
		}
		
		if (verificaDataHoraOcupada(locacao)) {
			model.addAttribute("locadoras", locadoraService.buscarTodos());
			attr.addFlashAttribute("fail", "locacao.create.fail");
			return "redirect:/locacoes/cadastrar";
		}
		
		locacao.setCliente(getClienteAutenticado());
		locacaoService.salvar(locacao);
		
		attr.addFlashAttribute("sucess", "locacao.create.sucess");
		return "redirect:/locacoes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("locadoras", locadoraService.buscarTodos());
		model.addAttribute("locacao", locacaoService.buscarPorId(id));
		return "locacao/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("locadoras", locadoraService.buscarTodos());
			return "locacao/cadastro";
		}
		
		locacao.setCliente(getClienteAutenticado());
		locacaoService.salvar(locacao);
		
		attr.addFlashAttribute("sucess", "locacao.edit.sucess");
		return "redirect:/locacoes/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		locacaoService.excluir(id);
		model.addAttribute("sucess", "locacao.delete.sucess");
		return "redirect:/locacoes/listar";
	}
}
