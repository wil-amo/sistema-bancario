package main.java.V3.SPRING.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CriarContaDTO {

    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "Tipo de conta é obrigatório (CORRENTE ou POUPANCA)")
    private String tipoConta;

    @NotNull(message = "Agência é obrigatória")
    @Positive(message = "Agência deve ser um número positivo")
    private Integer agencia;

    @NotNull(message = "Número da conta é obrigatório")
    @Positive(message = "Número da conta deve ser um número positivo")
    private Integer numero;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}