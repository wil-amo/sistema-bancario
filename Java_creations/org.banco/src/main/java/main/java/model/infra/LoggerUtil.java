package main.java.model.infra;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {

    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        try {
            //grava arquivo "app-log.txt"
            FileHandler fileHandler = new FileHandler("app-log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter()); // txt
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // não duplica no console
        } catch (IOException e) {
            System.err.println("Falha ao configurar o logger: " + e.getMessage());
        }
    }

    private LoggerUtil() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void logSevere(String mensagem, Throwable erro) {
        logger.log(Level.SEVERE, mensagem, erro);
    }

    public static void logInfo(String mensagem) {
        logger.log(Level.INFO, mensagem);
    }
}