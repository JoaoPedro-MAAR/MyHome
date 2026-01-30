package br.com.edu.ifpb.pps;

import br.com.edu.ifpb.pps.configuracao.Configuracao;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Usuario;
import br.com.edu.ifpb.pps.moderacao.ModeracaoHandler;
import br.com.edu.ifpb.pps.moderacao.ModeracaoPreco;
import br.com.edu.ifpb.pps.moderacao.ModeracaoTermo;

public class Main {
    public static void main(String[] args) {
        

        Usuario user = new Usuario("João", "joao@email.com");

        Anuncio anuncioRuim = new Anuncio("Anuncio 1 Legal", "Descricao do anuncio 1", 100, user);
        Anuncio anuncioBom = new Anuncio("Anuncio 2", "Descricao do anuncio 2", 0, user);
        
        ModeracaoHandler validacao = new ModeracaoTermo();
        validacao.setNext(new ModeracaoPreco());

        System.out.println(" teste anuncio ruim");
        boolean resultadoRuim = validacao.moderar(anuncioRuim);
        System.out.println("resultado: " + (resultadoRuim ? "aprovado" : "rejeitado"));
        

        System.out.println(" teste anuncio bom");
        boolean resultadoBom = validacao.moderar(anuncioBom);
        System.out.println("resultado: " + (resultadoBom ? "aprovado" : "rejeitado"));
    }
}