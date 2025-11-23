package main.java.V2.JDBC.Infra.logger;

import java.util.logging.*;

public class LoggerUtil {

    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

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