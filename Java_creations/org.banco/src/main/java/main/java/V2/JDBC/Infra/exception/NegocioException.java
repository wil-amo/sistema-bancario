package main.java.V2.JDBC.Infra.exception;

public class NegocioException extends Exception {
    public NegocioException(String mensagem) {
        super(mensagem);
    }
}
