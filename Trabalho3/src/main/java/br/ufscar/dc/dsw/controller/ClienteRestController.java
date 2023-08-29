package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@RestController
public class ClienteRestController {

	@Autowired
	private IClienteService ClienteService;
	
	@Autowired
	private IUsuarioService usuarioService;
	private void parse(Cliente cliente, JSONObject json) {
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				cliente.setId(((Integer) id).longValue());
			} else {
				cliente.setId((Long) id);
			}
		}

		cliente.setCPF((String) json.get("cpf"));
		cliente.setName((String) json.get("name"));
		cliente.setUsername((String) json.get("username"));
		cliente.setPhone((String) json.get("phone"));
		cliente.setPassword((String) json.get("password"));
		cliente.setRole((String) json.get("role"));
		cliente.setEnabled(true);
		cliente.setGender((String) json.get("gender"));
		cliente.setDataNascimento((String) json.get("dataNascimento"));
	}
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	@PostMapping(path = "/clientes1")
	@ResponseBody
	public ResponseEntity<Cliente> create(@RequestBody JSONObject json) {
		System.out.println("oi");
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = new Cliente();
				parse(cliente, json);
				usuarioService.salvar(cliente);
				return ResponseEntity.ok(cliente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	@GetMapping(path = "/clientes")
	public ResponseEntity<List<Cliente>> lista() {
		List<Cliente> lista = ClienteService.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> listaPorId(@PathVariable("id") Long id) {
		Cliente cliente = ClienteService.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}
	@DeleteMapping(path = "/clientes/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") Long id) {
		System.out.println("estou aqui");
		Cliente cliente = ClienteService.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else if (!cliente.getLocacoes().isEmpty()) {
			return ResponseEntity.ok(false);
		} else {
			usuarioService.excluir(id);
			return ResponseEntity.ok(true);
		}
	}
	
}
