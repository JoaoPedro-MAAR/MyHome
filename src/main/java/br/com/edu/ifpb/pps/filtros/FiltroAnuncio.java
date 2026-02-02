package br.com.edu.ifpb.pps.filtros;

import br.com.edu.ifpb.pps.model.Anuncio;

import java.util.List;

public interface FiltroAnuncio {
    List<Anuncio> filtrar(List<Anuncio> conteudo);
}
