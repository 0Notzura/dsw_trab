package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
		List<Locacao> locacoes = locacaoService.buscarPorLocadora(locacao.getLocadora());
		
		for (int i = 0; i < locacoes.size(); i++) {
			if (locacoes.get(i).getDataRes().equals(locacao.getDataRes())
					&& locacoes.get(i).getHourRes() == locacao.getHourRes()) {
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
		List<Locacao> locacoes = locacaoService.buscarPorCliente(getClienteAutenticado());

		model.addAttribute("locacoes", locacoes);
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
			attr.addFlashAttribute("fail", "Locação não inserida. Horário ocupado.");
			return "redirect:/locacoes/cadastrar";
		}
		
		locacao.setCliente(getClienteAutenticado());
		locacaoService.salvar(locacao);
		
		attr.addFlashAttribute("sucess", "Locação inserida com sucesso.");
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
		
//		Se está editando o horário está disponível
//		if (verificaDataHoraOcupada(locacao)) {
//			model.addAttribute("locadoras", locadoraService.buscarTodos());
//			attr.addFlashAttribute("fail", "Locação não inserida. Horário ocupado.");
//			return "redirect:/locacoes/cadastrar";
//		}
		
		locacao.setCliente(getClienteAutenticado());
		locacaoService.salvar(locacao);
		
		attr.addFlashAttribute("sucess", "Locação inserida com sucesso.");
		return "redirect:/locacoes/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		locacaoService.excluir(id);
		
		return "redirect:/locacoes/listar";
	}
}
