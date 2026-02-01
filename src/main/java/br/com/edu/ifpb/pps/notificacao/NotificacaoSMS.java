package br.com.edu.ifpb.pps.notificacao;

public class NotificacaoSMS implements MeioDeNotificacao {

    @Override
    public void enviar(String mensagem, String destinatario) {
        System.out.println("SMS - Enviado para" + destinatario + ": " + mensagem);
    }
}
