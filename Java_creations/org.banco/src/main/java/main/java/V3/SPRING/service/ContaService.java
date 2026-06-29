package main.java.V3.SPRING.service;

import main.java.V3.SPRING.model.Cliente;
import main.java.V3.SPRING.model.Conta;
import main.java.V3.SPRING.model.ContaCorrente;
import main.java.V3.SPRING.model.ContaPoupanca;
import main.java.V3.SPRING.repository.ClienteRepository;
import main.java.V3.SPRING.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Conta criarConta(Long clienteId, String tipoConta, Integer agencia, Integer numero) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + clienteId));

        if (contaRepository.existsByNumero(numero)) {
            throw new IllegalArgumentException("Número de conta já existe: " + numero);
        }

        Conta conta;
        if ("CORRENTE".equalsIgnoreCase(tipoConta)) {
            conta = new ContaCorrente(cliente, agencia, numero);
        } else if ("POUPANCA".equalsIgnoreCase(tipoConta)) {
            conta = new ContaPoupanca(cliente, agencia, numero);
        } else {
            throw new IllegalArgumentException("Tipo de conta inválido: " + tipoConta);
        }

        return contaRepository.save(conta);
    }

    public List<Conta> listarTodas() {
        return contaRepository.findAll();
    }

    public Optional<Conta> buscarPorNumero(Integer numero) {
        return contaRepository.findByNumero(numero);
    }

    @Transactional
    public void depositar(Integer numeroConta, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo");
        }

        Conta conta = contaRepository.findByNumero(numeroConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada com número: " + numeroConta));

        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);
    }

    @Transactional
    public void sacar(Integer numeroConta, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo");
        }

        Conta conta = contaRepository.findByNumero(numeroConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada com número: " + numeroConta));

        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);
    }

    @Transactional
    public void transferir(Integer numeroContaOrigem, Integer numeroContaDestino, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de transferência deve ser positivo");
        }

        Conta origem = contaRepository.findByNumero(numeroContaOrigem)
                .orElseThrow(() -> new IllegalArgumentException("Conta de origem não encontrada com número: " + numeroContaOrigem));

        Conta destino = contaRepository.findByNumero(numeroContaDestino)
                .orElseThrow(() -> new IllegalArgumentException("Conta de destino não encontrada com número: " + numeroContaDestino));

        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente na conta de origem");
        }

        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));

        contaRepository.save(origem);
        contaRepository.save(destino);
    }
}