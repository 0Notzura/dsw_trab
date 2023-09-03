package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

import java.util.List;

@RestController
public class LocacaoRestController {
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ILocacaoService locacaoService;

    @Autowired
    private ILocadoraService locadoraService;

    @GetMapping("/locacoes")
    public ResponseEntity<List<Locacao>> getAllLocacoes() {
        List<Locacao> locacoes = locacaoService.buscarTodos();
        if (locacoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping("/locacoes/{id}")
    public ResponseEntity<Locacao> getLocacaoById(@PathVariable("id") Long id) {
        Locacao locacao = locacaoService.buscarPorId(id);
        if (locacao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locacao);
    }

    @GetMapping("/locacoes/clientes/{id}")
    public ResponseEntity<List<Locacao>> getLocacoesByClienteId(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        List<Locacao> locacoes = locacaoService.buscarPorCliente(cliente);
        if (locacoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping("/locacoes/locadoras/{id}")
    public ResponseEntity<List<Locacao>> getLocacoesByLocadoraId(@PathVariable("id") Long id) {
        Locadora locadora = locadoraService.buscarPorId(id);
        if (locadora == null) {
            return ResponseEntity.notFound().build();
        }
        List<Locacao> locacoes = locacaoService.buscarPorLocadora(locadora);
        if (locacoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locacoes);
    }
}
