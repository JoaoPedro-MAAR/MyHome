package br.com.edu.ifpb.pps.ImovelBuilder;

public class DiretorImovel {

    public void fazerCasaPadrao(CasaBuilder builder) {
        builder.reset();

        builder.setArea(120.0);
        builder.setLocalizacao(new Double[]{-7.1, -34.8});
        builder.setFinalidade("Venda");

        builder.setTemJardim(true);
        builder.setQtdQuartos(2);
    }


    public void fazerApartamentoPadrao(ApartamentoBuilder builder) {
        builder.reset();
        builder.setArea(60.0);
        builder.setFinalidade("Aluguel");

        builder.setAndar(1);
        builder.setTemElevador(false);
    }
}