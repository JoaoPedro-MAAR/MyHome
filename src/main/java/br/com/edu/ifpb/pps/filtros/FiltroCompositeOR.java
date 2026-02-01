package br.com.edu.ifpb.pps.filtros;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.edu.ifpb.pps.model.Anuncio;

public class FiltroCompositeOR implements FiltroAnuncio {

    private List<FiltroAnuncio> filtros;

    public FiltroCompositeOR(List<FiltroAnuncio> filtros) {
        this.filtros = filtros;
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> conteudo) {
        Set<Anuncio> resultados = new LinkedHashSet<>();
        for (FiltroAnuncio filtro : filtros) {
            resultados.addAll(filtro.filtrar(conteudo));
        }
        return new ArrayList<>(resultados);
    }

    public void adicionarFiltro(FiltroAnuncio filtro) {
        filtros.add(filtro);
    }

    public void removerFiltro(FiltroAnuncio filtro) {
        filtros.remove(filtro);
    }

    public List<FiltroAnuncio> getFiltros() {
        return filtros;
    }

    public void clearFiltros() {
        filtros.clear();
    }

}
