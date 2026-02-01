package br.com.edu.ifpb.pps.filtros;

import java.util.List;

import br.com.edu.ifpb.pps.filtros.visitors.FiltroSomenteTipoImovelVisitor;
import br.com.edu.ifpb.pps.filtros.visitors.TipoImovel;
import br.com.edu.ifpb.pps.model.Anuncio;

public class FiltroTipoImovel implements FiltroAnuncio {
    private FiltroSomenteTipoImovelVisitor visitor = new FiltroSomenteTipoImovelVisitor();

    private List<TipoImovel> tiposAceitos;
    
    public FiltroTipoImovel(List<TipoImovel> tiposAceitos) {
        this.tiposAceitos = tiposAceitos;
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> conteudo) {
        return conteudo.stream()
                .filter(a -> visitor.isAprovado(a.getImovel(), tiposAceitos))
                .toList();
    }

    public List<TipoImovel> getTiposAceitos() {
        return tiposAceitos;
    }

    public void addTipoAceito(TipoImovel tipo) {
        this.tiposAceitos.add(tipo);
    }

    public void removeTipoAceito(TipoImovel tipo) {
        this.tiposAceitos.remove(tipo);
    }

}
