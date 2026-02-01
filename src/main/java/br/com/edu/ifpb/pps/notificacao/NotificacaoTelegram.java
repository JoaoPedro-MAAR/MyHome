package br.com.edu.ifpb.pps.notificacao;

public class NotificacaoTelegram implements MeioDeNotificacao {

    @Override
    public void enviar(String mensagem, String destinatario) {
        System.out.println("Telegram - Enviado para" + destinatario + ": " + mensagem);
    }
}
