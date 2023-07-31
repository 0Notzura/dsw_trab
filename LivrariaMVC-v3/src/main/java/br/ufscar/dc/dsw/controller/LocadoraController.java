package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.domain.Locadora;

@Controller
@RequestMapping("/editoras")
public class LocadoraController {
	

	@Autowired
	private IUsuarioService UsuarioService;

	@Autowired
	private ILocadoraService LocadoraService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Locacao editora) {	
		return "editora/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("editoras",LocadoraService.buscarTodos());
		return "editora/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {

		if (result.hasErrors()) {
			return "editora/cadastro";
		}
		locadora.setPassword(encoder.encode(locadora.getPassword()));
		UsuarioService.salvar(locadora);
		attr.addFlashAttribute("sucess", "editora.create.sucess");
		return "redirect:/editoras/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("editora", LocadoraService.buscarPorId(id));
		return "editora/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
		
		// Apenas rejeita se o problema não for com o CNPJ (CNPJ campo read-only) 
		
		if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) {
			return "editora/cadastro";
		}

		UsuarioService.salvar(locadora);
		attr.addFlashAttribute("sucess", "editora.edit.sucess");
		return "redirect:/editoras/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (LocadoraService.locadoraTemLocacao(id)) {
			model.addAttribute("fail", "editora.delete.fail");
		} else {
			UsuarioService.excluir(id);
			model.addAttribute("sucess", "editora.delete.sucess");
		}
		return listar(model);
	}
}
