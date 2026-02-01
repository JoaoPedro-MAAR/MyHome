package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class AtivoState implements EstadoAnuncio {

    private Anuncio anuncio;

    @Override
    public void setContext(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    @Override
    public void vender() {
        this.anuncio.setEstado(new VendidoState());
    }

    @Override
    public void suspender() {
        this.anuncio.setEstado(new SuspensoState());
    }


     public void editar() {
        this.anuncio.setEstado(new RascunhoState());
    }

    public void enviarParaModeracao() {
        
    }
   
    public void aprovar() {
       
    }

    public void reprovar() {
        
    }

    @Override
    public String getNome() {
        return "Ativo";
    }

}   
