package main.java.V1.POO.Exception;

public class TaxaExeception extends DominioException{
    public TaxaExeception(String mensagem, double valor) {
        super(mensagem + String.format("%.2f", valor));
    }
}
