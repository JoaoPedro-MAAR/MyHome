package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class RascunhoState implements EstadoAnuncio {

    private Anuncio anuncio;

    @Override
    public void setContext(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    @Override
    public void enviarParaModeracao() {
        this.anuncio.setEstado(new PendenteModeracaoState());
    }

    public void aprovar(){
    }

    public void reprovar(){
        
    }

    public void suspender(){
        
    }

    public void vender(){
       
    }

    public void editar(){
       
    }

    @Override
    public String getNome() {
        return "Rascunho";
    }
}
