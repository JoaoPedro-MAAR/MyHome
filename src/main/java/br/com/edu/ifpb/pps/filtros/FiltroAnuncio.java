package br.com.edu.ifpb.pps.filtros;

import java.util.List;

import br.com.edu.ifpb.pps.model.Anuncio;

public interface FiltroAnuncio {
    List<Anuncio> filtrar(List<Anuncio> conteudo);
}
