package br.com.edu.ifpb.pps.Facade;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;

public class facadeTeste {
    public static void main(String[] args) {
        ClientFacade fachada = new ClientFacade();

        String email = "joao@email.com";
        System.out.println(fachada.login(email));
        System.out.println(fachada.getUsuarioLogado());

        AnuncioDTO dtoAnun = new AnuncioDTO();
        dtoAnun.titulo = "Ola minions";
        dtoAnun.preco = 20.000;
        dtoAnun.tipo = "CASA";
        ImovelDTO dtoImo = new ImovelDTO();
        dtoImo.qtdQuartos=4;
        dtoImo.area=200.0;
        dtoImo.finalidade = FinalidadeEnum.ALUGUEL;
        dtoImo.localizacao = new Double[]{124.745,133.523};
        fachada.criarAnuncio(dtoAnun, dtoImo);

    }
}
