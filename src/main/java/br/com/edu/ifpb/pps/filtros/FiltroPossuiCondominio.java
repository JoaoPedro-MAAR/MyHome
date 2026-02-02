package br.com.edu.ifpb.pps.filtros;

import br.com.edu.ifpb.pps.filtros.visitors.FiltroImovelComCondominioVisitor;
import br.com.edu.ifpb.pps.model.Anuncio;

import java.util.List;

public class FiltroPossuiCondominio implements FiltroAnuncio {

    private boolean possuiCondominio;
    private FiltroImovelComCondominioVisitor visitor = new FiltroImovelComCondominioVisitor();

    public FiltroPossuiCondominio(boolean possuiCondominio) {
        this.possuiCondominio = possuiCondominio;
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> conteudo) {
        return conteudo.stream()
                .filter(a -> visitor.isAprovado(a.getImovel(), possuiCondominio))
                .toList();
    }

}
