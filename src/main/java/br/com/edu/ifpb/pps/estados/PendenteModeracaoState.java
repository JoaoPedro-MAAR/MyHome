package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class PendenteModeracaoState  implements EstadoAnuncio {
    @Override
    public void aprovar(Anuncio anuncio) {
       anuncio.setEstado(new AtivoState());
    }

    @Override
    public void reprovar(Anuncio anuncio) {
        anuncio.setEstado(new SuspensoState());
    }

    @Override
    public void enviarParaModeracao(Anuncio anuncio) {
        System.out.println("Erro");
    }

    @Override
    public void suspender(Anuncio anuncio) {
        System.out.println("Erro");
    }

    @Override
    public void vender(Anuncio anuncio) {
        System.out.println("Erro");
    }

    @Override
    public void editar(Anuncio anuncio) {
        System.out.println("Erro");
    }

    @Override
    public String getNome() {
        return "Pendente de Moderação";
    }

}
