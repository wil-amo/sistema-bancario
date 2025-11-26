package main.java.V1.POO.Exception;

public class MovimentacaoInvalidaException extends DominioException{

public MovimentacaoInvalidaException(String e, double valor){
    super(e + String.format("%.2f", valor));
}

}
