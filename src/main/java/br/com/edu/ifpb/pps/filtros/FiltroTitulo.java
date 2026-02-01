package br.com.edu.ifpb.pps.filtros;

import java.util.List;

import br.com.edu.ifpb.pps.model.Anuncio;

public class FiltroTitulo extends FiltroSimples {

    public FiltroTitulo(String parametro) {
        super(parametro, nome -> nome != null && nome.contains(parametro));
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> conteudo) {
        
        return conteudo.stream()
                .filter(anuncio -> super.criterio.test(anuncio.getTitulo()))
                .toList();
    }

    


}
