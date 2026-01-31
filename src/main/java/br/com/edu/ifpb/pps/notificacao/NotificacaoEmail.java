package br.com.edu.ifpb.pps.notificacao;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotificacaoEmail implements MeioDeNotificacao {

    private final String email = "lucas.toscano@academico.ifpb.edu.br";
    private final String senha = "zxdh zvjj ohbb jvcl";
    @Override
    public void enviar(String mensagem, String destinatario) {
        Properties propiedades = new Properties();

        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(propiedades, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, senha);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Notificação MyHome");
            message.setText(mensagem);

            Transport.send(message);

            System.out.println("Email Enviado");
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar email");
            e.printStackTrace();
        }
    }

}
