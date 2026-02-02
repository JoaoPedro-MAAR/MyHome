package br.com.edu.ifpb.pps;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.Facade.ClientFacade;

import java.util.List;

public class main {
    public static void main(String[] args) {
        ClientFacade fachada = new ClientFacade();
        String email = "araujo.aragao@academico.ifpb.edu.br";
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
        System.out.println(fachada.getAllAnuncio().size());
        fachada.criarAnuncio(dtoAnun, dtoImo);
        System.out.println(fachada.getAllAnuncio().size());
        List<String> presets = fachada.getAllPresets();
        System.out.println(presets);
        System.out.println(fachada.getanuncioConfigByChave(presets.getFirst()));
        fachada.criarConfig("PRESET1", dtoAnun, dtoImo);
        System.out.println(fachada.getAllPresets());
        System.out.println(fachada.getanuncioConfigByChave("OLA_MINIONS"));
        System.out.println(fachada.buscarPorId(1));
        fachada.comprarAnuncio(1);
        System.out.println(fachada.buscarPorId(1));


    }
}
