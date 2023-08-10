package br.ufscar.dc.dsw.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.HashSet;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.impl.LocadoraService;
import br.ufscar.dc.dsw.service.impl.UsuarioService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/locadoras")
public class LocadoraController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ILocadoraService locadoraService;

@GetMapping("/cadastrar")
	public String cadastrar(Locadora locadora) {
		return "locadora/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(@RequestParam(required = false) String c, ModelMap model) {
		List<Locadora> locadoras = locadoraService.buscarTodos();
		Set<String> cidades = new HashSet<String>();

		for (Locadora locadora : locadoras) {
			String cidade = locadora.getCidade();
            if (!cidades.contains(cidade)) {
                cidades.add(cidade);
            }
		}

		if (c != null && !c.isEmpty()) {
			locadoras = locadoraService.buscarPorCidade(c);
		}

		model.addAttribute("locadoras", locadoras);
		model.addAttribute("cidades", cidades);
		return "locadora/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		
		if (result.hasErrors()) {
			return "locadora/cadastro";
		}
		
		locadora.setPassword(encoder.encode(locadora.getPassword())); 
		usuarioService.salvar(locadora);
		attr.addFlashAttribute("sucess", "locadora.create.sucess");
		return "redirect:/locadoras/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("locadora", usuarioService.buscarPorId(id));
		return "locadora/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
		if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) {
			System.out.println(result);
			return "locadora/cadastro";
		}	

		usuarioService.salvar(locadora);
		attr.addFlashAttribute("sucess", "locadora.edit.sucess");
		return "redirect:/locadoras/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (locadoraService.locadoraTemLocacao(id)) {
			attr.addFlashAttribute("fail", "locadora.delete.fail");
		}
		else {
			usuarioService.excluir(id);
			attr.addFlashAttribute("sucess", "locadora.delete.sucess");
		}
		return "redirect:/locadoras/listar";
	}
}