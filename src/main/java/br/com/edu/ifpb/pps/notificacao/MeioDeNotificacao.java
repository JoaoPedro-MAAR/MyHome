package br.com.edu.ifpb.pps.notificacao;

public interface MeioDeNotificacao {

    void enviar(String mensagem, String destinatario);
}
