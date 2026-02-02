package br.com.edu.ifpb.pps.notificacao;

import br.com.edu.ifpb.pps.configuracao.Configuracao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class NotificacaoEmail implements MeioDeNotificacao {

    
    @Override
    public void enviar(String mensagem, String destinatario) {
        String email = Configuracao.getInstance().getPropriedade("email");
        String senha = Configuracao.getInstance().getPropriedade("senha");

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

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
