package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Controller
@RequestMapping("/locacaos")
public class LocacaoController {
	
	@Autowired
	private ILocacaoService locacaoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private ILocadoraService locadoraService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Locacao locacao) {
		String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		locacao.setUsuario(this.getUsuario());
		locacao.setData(data);
		return "locacao/cadastro";
	}
	
	private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
					
		model.addAttribute("locacaos",service.buscarTodosPorUsuario(this.getUsuario()));
		
		return "locacao/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "locacao/cadastro";
		}
		
		service.salvar(locacao);
		attr.addFlashAttribute("sucess", "Compra inserida com sucesso.");
		return "redirect:/locacaos/listar";
	}
	
	@ModelAttribute("livros")
	public List<Cliente> listaClientes() {
		return livroService.buscarTodos();
	}
}
