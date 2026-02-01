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
        System.out.println("Erro");
    }

    @Override
    public void suspender() {
        System.out.println("Erro");
    }

    @Override
    public void vender() {
        System.out.println("Erro");
    }

    @Override
    public void editar() {
        System.out.println("Erro");
    }

    @Override
    public String getNome() {
        return "Pendente de Moderação";
    }

}
