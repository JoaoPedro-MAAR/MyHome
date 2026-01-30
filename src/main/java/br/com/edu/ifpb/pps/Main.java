package br.com.edu.ifpb.pps;

import br.com.edu.ifpb.pps.configuracao.Configuracao;

public class Main {
    public static void main(String[] args) {
    
        System.out.println("teste rf07");

        Configuracao configuracao = Configuracao.getInstance();

        System.out.println("Limite de fotos" + configuracao.getLimiteUploadFotos());
        System.out.println("Taxa de comissao" + configuracao.getTaxaComissao());
        System.out.println("Termos proibidos" + configuracao.getTermosProbidos());

    }
}