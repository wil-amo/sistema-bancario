package main.java.V3.SPRING.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransferenciaDTO {

    @NotNull(message = "Número da conta de origem é obrigatório")
    private Integer numeroContaOrigem;

    @NotNull(message = "Número da conta de destino é obrigatório")
    private Integer numeroContaDestino;

    @NotNull(message = "Valor da transferência é obrigatório")
    @Positive(message = "Valor da transferência deve ser positivo")
    private BigDecimal valor;

    public Integer getNumeroContaOrigem() {
        return numeroContaOrigem;
    }

    public void setNumeroContaOrigem(Integer numeroContaOrigem) {
        this.numeroContaOrigem = numeroContaOrigem;
    }

    public Integer getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(Integer numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}