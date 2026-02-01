package br.com.edu.ifpb.pps.filtros;

import java.util.List;

import br.com.edu.ifpb.pps.model.Anuncio;

public class FiltroCompositeAND implements FiltroAnuncio {

    private List<FiltroAnuncio> filtros;

    public FiltroCompositeAND(List<FiltroAnuncio> filtros) {
        this.filtros = filtros;
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> conteudo) {
        List<Anuncio> resultados = conteudo;
        for (FiltroAnuncio filtro : filtros) {
            resultados = filtro.filtrar(resultados);
        }
        return resultados;
    }

    public void adicionarFiltro(FiltroAnuncio filtro) {
        filtros.add(filtro);
    }

    public void removerFiltro(FiltroAnuncio filtro) {
        filtros.remove(filtro);
    }

    public void clearFiltros() {
        filtros.clear();
    }
}
