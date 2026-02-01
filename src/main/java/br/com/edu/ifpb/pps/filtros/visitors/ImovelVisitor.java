package br.com.edu.ifpb.pps.filtros.visitors;

import br.com.edu.ifpb.pps.model.Imovel.Apartamento;
import br.com.edu.ifpb.pps.model.Imovel.Casa;

public interface ImovelVisitor {
    void visitar(Casa casa);
    void visitar(Apartamento apartamento);
    // void visitarTerreno(Terreno terreno);
}
