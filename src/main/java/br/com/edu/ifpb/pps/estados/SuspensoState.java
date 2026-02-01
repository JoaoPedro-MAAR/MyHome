package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class SuspensoState implements EstadoAnuncio {

    private Anuncio anuncio;

    @Override
    public void setContext(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    @Override
    public void editar() {
        this.anuncio.setEstado(new RascunhoState());
    }

    public void enviarParaModeracao() {
        
    }

    public void aprovar(){
        
    }

    public void reprovar(){
        
    }

    public void vender(){
        
    }

    public void suspender(){
        
    }

    @Override
    public String getNome() {
        return "Suspenso";
    }
}
