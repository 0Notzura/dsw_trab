package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IUsuarioService usuarioService;

    private void parse(Cliente cliente, JSONObject json) {
        Object id = json.get("id");
        if (id != null) {
            if (id instanceof Integer) {
                cliente.setId(((Long) id).longValue());
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

    @PostMapping
    @ResponseBody
    public ResponseEntity<Cliente> createCliente(@RequestBody JSONObject json, BCryptPasswordEncoder encoder) {
        try {
            if (isJSONValid(json.toString())) {
                Cliente cliente = new Cliente();
                parse(cliente, json);
                cliente.setPassword(encoder.encode(cliente.getPassword()));
                usuarioService.salvar(cliente);
                return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Long id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Cliente cliente = clienteService.buscarPorId(id);
                if (cliente == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    parse(cliente, json);
                    usuarioService.salvar(cliente);
                    return ResponseEntity.ok(cliente);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listClientes() {
        List<Cliente> clientes = clienteService.buscarTodos();
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCliente(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
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
