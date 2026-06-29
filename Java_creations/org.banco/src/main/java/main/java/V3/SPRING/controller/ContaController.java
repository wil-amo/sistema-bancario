package main.java.V3.SPRING.controller;

import jakarta.validation.Valid;
import main.java.V3.SPRING.dto.CriarContaDTO;
import main.java.V3.SPRING.dto.DepositoSaqueDTO;
import main.java.V3.SPRING.dto.TransferenciaDTO;
import main.java.V3.SPRING.model.Conta;
import main.java.V3.SPRING.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/contas")
public class ContaController {

    private final ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<?> criarConta(@Valid @RequestBody CriarContaDTO dto) {
        try {
            Conta novaConta = contaService.criarConta(dto.getClienteId(), dto.getTipoConta(), dto.getAgencia(), dto.getNumero());
            return new ResponseEntity<>(novaConta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listarContas() {
        List<Conta> contas = contaService.listarTodas();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Conta> buscarConta(@PathVariable Integer numero) {
        return contaService.buscarPorNumero(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{numero}/deposito")
    public ResponseEntity<?> depositar(@PathVariable Integer numero, @Valid @RequestBody DepositoSaqueDTO dto) {
        try {
            contaService.depositar(numero, dto.getValor());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{numero}/saque")
    public ResponseEntity<?> sacar(@PathVariable Integer numero, @Valid @RequestBody DepositoSaqueDTO dto) {
        try {
            contaService.sacar(numero, dto.getValor());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/transferencia")
    public ResponseEntity<?> transferir(@Valid @RequestBody TransferenciaDTO dto) {
        try {
            contaService.transferir(dto.getNumeroContaOrigem(), dto.getNumeroContaDestino(), dto.getValor());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}