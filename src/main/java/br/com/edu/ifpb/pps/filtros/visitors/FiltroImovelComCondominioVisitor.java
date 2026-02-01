package br.com.edu.ifpb.pps.filtros.visitors;

import br.com.edu.ifpb.pps.model.Imovel.Apartamento;
import br.com.edu.ifpb.pps.model.Imovel.Casa;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;

public class FiltroImovelComCondominioVisitor implements ImovelVisitor {
    private boolean temCondominio = false;

    @Override
    public void visitar(Casa casa) {
        temCondominio = false;
    }

    @Override
    public void visitar(Apartamento apartamento) {
        temCondominio = apartamento.getTemCondominio() != null && apartamento.getTemCondominio();
    }

    public boolean isAprovado(Imovel imovel, boolean desejaCondominio) {
        temCondominio = false;
        imovel.aceitar(this);
        return temCondominio == desejaCondominio;
    }

}
