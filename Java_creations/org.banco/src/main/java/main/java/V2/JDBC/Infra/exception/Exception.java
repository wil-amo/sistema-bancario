package main.java.V2.JDBC.Infra.exception;

//realizar testes visuais, dando clareza e simular atraso
public class Exception {
    public static void esperar(long milissegundos) throws InterruptedException {
        Thread.sleep(milissegundos);
    }


    //facilitar impressão
    public static void i(Object msg) {
        System.out.println(msg);
    }


}
