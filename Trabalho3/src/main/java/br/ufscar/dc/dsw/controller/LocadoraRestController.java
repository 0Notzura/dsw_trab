package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/locadoras")
public class LocadoraRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ILocadoraService locadoraService;

    @GetMapping
    public ResponseEntity<List<Locadora>> listLocadoras() {
        List<Locadora> locadoras = locadoraService.buscarTodos();
        if (locadoras.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locadoras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locadora> getLocadora(@PathVariable("id") Long id) {
        Locadora locadora = locadoraService.buscarPorId(id);
        if (locadora == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locadora);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLocadora(@PathVariable("id") Long id) {
        Locadora locadora = locadoraService.buscarPorId(id);
        if (locadora == null) {
            return ResponseEntity.notFound().build();
        } else if (!locadora.getLocacoes().isEmpty()) {
            return ResponseEntity.ok(false);
        } else {
            usuarioService.excluir(id);
            return ResponseEntity.ok(true);
        }
    }

    @GetMapping("/cidades/{nome}")
    public ResponseEntity<List<Locadora>> listLocadorasByCity(@PathVariable("nome") String nome) {
        List<Locadora> locadoras = locadoraService.buscarPorCidade(nome);
        if (locadoras == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locadoras);
    }

    @PostMapping
    public ResponseEntity<Locadora> createLocadora(@RequestBody Locadora locadora, BCryptPasswordEncoder encoder) {
        try {
            locadora.setPassword(encoder.encode(locadora.getPassword()));
            usuarioService.salvar(locadora);
            return ResponseEntity.status(HttpStatus.CREATED).body(locadora);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locadora> updateLocadora(@PathVariable("id") Long id, @RequestBody Locadora locadora) {
        Locadora existingLocadora = locadoraService.buscarPorId(id);
        if (existingLocadora == null) {
            return ResponseEntity.notFound().build();
        } else {
            locadora.setId(existingLocadora.getId());
            usuarioService.salvar(locadora);
            return ResponseEntity.ok(locadora);
        }
    }
}
