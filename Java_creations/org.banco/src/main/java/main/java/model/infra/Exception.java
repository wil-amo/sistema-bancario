package main.java.model.infra;

//realizar testes visuais, dando clareza e simular atraso
public class Exception {
    public static void esperar(long milissegundos) throws InterruptedException {
        Thread.sleep(milissegundos);
    }

}
