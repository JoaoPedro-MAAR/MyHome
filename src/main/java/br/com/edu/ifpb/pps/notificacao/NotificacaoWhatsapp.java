package br.com.edu.ifpb.pps.notificacao;

public class NotificacaoWhatsapp implements MeioDeNotificacao {
    @Override
    public void enviar(String mensagem, String destinatario) {
        System.out.println("Whatsapp - Enviado para" + destinatario + ": " + mensagem);
    }

}
