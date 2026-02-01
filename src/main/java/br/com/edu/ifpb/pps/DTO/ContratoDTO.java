package br.com.edu.ifpb.pps.DTO;

import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Usuario;

public class ContratoDTO {
    public String id;
    public String cabecalho;
    public String[] clausulas;
    public Usuario usuario;
    public Anuncio anuncio;
}
