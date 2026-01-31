package br.com.edu.ifpb.pps.notificacao;

public class NotificacaoEmail implements MeioDeNotificacao {

    @Override
    public void enviar(String mensagem, String destinatario) {
        System.out.println("Email - Enviado para" + destinatario + ": " + mensagem);
    }

}
