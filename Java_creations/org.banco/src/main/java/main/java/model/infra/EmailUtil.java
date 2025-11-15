package main.java.model.infra;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public final class EmailUtil {

    private EmailUtil() {
        throw new UnsupportedOperationException("Classe utilitária - não instanciar.");
    }

    public static boolean emailValido(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    //Ideal salvar em credenciais e fazer referência
    public static void enviarCodigo(String destinatario, String codigo) {
        final String remetente = "seu-email@gmail.com";
        final String senha = "SENHA-APP"; // Substitua por senha de app válida

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            Message mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(remetente));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensagem.setSubject("Seu código de verificação");
            mensagem.setText("Olá! Seu código de verificação é: " + codigo);

            Transport.send(mensagem);
            System.out.println("E-mail enviado com sucesso para " + destinatario);
        } catch (MessagingException e) {
            System.err.println("Falha ao enviar e-mail:");
            e.printStackTrace();
        }
    }
}