package br.com.edu.ifpb.pps;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifpb.pps.filtros.FiltroAnuncio;
import br.com.edu.ifpb.pps.filtros.FiltroCompositeOR;
import br.com.edu.ifpb.pps.filtros.FiltroFaixaPreco;
import br.com.edu.ifpb.pps.filtros.FiltroLocalizacao;
import br.com.edu.ifpb.pps.filtros.FiltroTitulo;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Usuario;
import br.com.edu.ifpb.pps.model.Imovel.Apartamento;
import br.com.edu.ifpb.pps.model.Imovel.Casa;

public class Main {
    public static void main(String[] args) {
        // Criar usuários
        Usuario usuario1 = new Usuario("João Silva", "joao@email.com");
        Usuario usuario2 = new Usuario("Maria Santos", "maria@email.com");
        Usuario usuario3 = new Usuario("Pedro Costa", "pedro@email.com");
        Usuario usuario4 = new Usuario("Ana Oliveira", "ana@email.com");

        // Criar anúncios de teste
        List<Anuncio> anuncios = new ArrayList<>();

        // Anúncio 1: Apartamento em João Pessoa, preço 250.0
        Apartamento apt1 = new Apartamento(85.0, new Double[]{-7.11514, -34.861}, "Venda", 3, true, true);
        Anuncio anuncio1 = new Anuncio("Apartamento no Centro", "Apartamento confortável com ótima localização", 250.0, usuario1, apt1);
        anuncios.add(anuncio1);

        // Anúncio 2: Casa em João Pessoa, preço 300.0
        Casa casa1 = new Casa(150.0, new Double[]{-7.11514, -34.861}, "Venda", true, 3, true);
        Anuncio anuncio2 = new Anuncio("Casa no Bairro dos Ipês", "Casa espaçosa com quintal amplo", 300.0, usuario2, casa1);
        anuncios.add(anuncio2);

        // Anúncio 3: Apartamento em outra localização, preço 180.0
        Apartamento apt2 = new Apartamento(70.0, new Double[]{-7.14554, -34.881}, "Venda", 1, true, false);
        Anuncio anuncio3 = new Anuncio("Apartamento próximo à universidade", "Quitinete para aluno", 180.0, usuario3, apt2);
        anuncios.add(anuncio3);

        // Anúncio 4: Casa em outra localização, preço 450.0
        Casa casa2 = new Casa(200.0, new Double[]{-7.08876, -34.846}, "Venda", true, 4, false);
        Anuncio anuncio4 = new Anuncio("Casa grande na Beira Rio", "Casa com 4 quartos e piscina", 450.0, usuario4, casa2);
        anuncios.add(anuncio4);

        // Anúncio 5: Apartamento fora da faixa de preço, preço 600.0
        Apartamento apt3 = new Apartamento(120.0, new Double[]{-7.11514, -34.861}, "Venda", 10, true, true);
        Anuncio anuncio5 = new Anuncio("Apartamento Duplex Luxo", "Apartamento de luxo com vista para o mar", 600.0, usuario1, apt3);
        anuncios.add(anuncio5);

        // Anúncio 6: Casa com preço muito baixo, preço 80.0
        Casa casa3 = new Casa(100.0, new Double[]{-7.16, -34.87}, "Venda", false, 2, true);
        Anuncio anuncio6 = new Anuncio("Casa antiga para reforma", "Casa antiga mas com terreno grande", 80.0, usuario2, casa3);
        anuncios.add(anuncio6);

        // Exibir anúncios criados
        System.out.println("=== LISTA DE ANÚNCIOS ===");
        for (int i = 0; i < anuncios.size(); i++) {
            Anuncio a = anuncios.get(i);
            System.out.println((i + 1) + ". " + a.getTitulo() + " - R$" + a.getPreco() + " - " + a.getAnunciante().getNome());
        }

        // Testar filtros
        System.out.println("\n=== TESTANDO FILTROS ===");
        List<FiltroAnuncio> filtros = List.of(
            new FiltroTitulo("Apartamento"),
            new FiltroLocalizacao(new Double[]{-7.11514, -34.861}),
            new FiltroFaixaPreco(100.0, 500.0)
        );

        FiltroAnuncio filtroComposto = new FiltroCompositeOR(filtros);
        
        List<Anuncio> resultado = filtroComposto.filtrar(anuncios);
        System.out.println("Anúncios após aplicação do filtro composto OR:");
        for (Anuncio a : resultado) {
            System.out.println("- " + a.getTitulo() + " - R$" + a.getPreco() + " - " + a.getAnunciante().getNome());
        }
    }
}