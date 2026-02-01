package br.com.edu.ifpb.pps.Facade;

import br.com.edu.ifpb.pps.Banco.Banco;
import br.com.edu.ifpb.pps.model.Usuario;
import br.com.edu.ifpb.pps.notificacao.MeioDeNotificacao;

public class UserFacade {
    private final Banco banco;
    private Usuario corrente;

    public UserFacade() {
        this.banco = Banco.getInstance();
    }

    public Boolean login(String email){
        if (banco.buscarUsuario(email) != null){
            corrente = banco.buscarUsuario(email);
            return true;
        };
        return false;
    }

    public void logout(){
        corrente = null;
    }

    public void setMeioDeNotificacao(MeioDeNotificacao meioDeNotificacao){
        if(corrente != null){
            corrente.setMeioDeNotificacao(meioDeNotificacao);
        }
    }

    public Usuario getCorrente() {
        return corrente;
    }
}
