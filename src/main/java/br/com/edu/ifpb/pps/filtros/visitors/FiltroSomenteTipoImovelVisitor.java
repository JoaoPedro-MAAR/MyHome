package br.com.edu.ifpb.pps.filtros.visitors;

import java.util.List;

import br.com.edu.ifpb.pps.model.Imovel.Apartamento;
import br.com.edu.ifpb.pps.model.Imovel.Casa;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;

public class FiltroSomenteTipoImovelVisitor implements ImovelVisitor {
    private boolean isCasa = false;
    private List<TipoImovel> tiposPermitidos;

    @Override
    public void visitar(Casa casa) {
        isCasa = tiposPermitidos.contains(TipoImovel.CASA);
    }

    @Override
    public void visitar(Apartamento apartamento) {
        isCasa = tiposPermitidos.contains(TipoImovel.APTO);
    }

    public boolean isAprovado(Imovel imovel, List<TipoImovel> tiposPermitidos) {
        this.tiposPermitidos = tiposPermitidos;
        isCasa = false;
        imovel.aceitar(this);
        return isCasa;
    }

}
