package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class PendenteModeracaoState implements EstadoAnuncio {

    private Anuncio anuncio;

    @Override
    public void setContext(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    @Override
    public void aprovar() {
       this.anuncio.setEstado(new AtivoState());
    }

    @Override
    public void reprovar() {
        this.anuncio.setEstado(new SuspensoState());
    }

    @Override
    public void enviarParaModeracao() {

    }

    @Override
    public void suspender() {

    }

    @Override
    public void vender() {
    }
    @Override
    public void editar() {

    }

    @Override
    public String getNome() {
        return "Pendente de Moderação";
    }

}
