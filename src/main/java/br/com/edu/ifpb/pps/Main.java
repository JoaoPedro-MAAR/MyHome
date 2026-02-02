package br.com.edu.ifpb.pps;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.Facade.ClientFacade;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Usuario;
import br.com.edu.ifpb.pps.notificacao.NotificacaoEmail;
import br.com.edu.ifpb.pps.notificacao.NotificacaoSMS;
import br.com.edu.ifpb.pps.notificacao.NotificacaoWhatsapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ClientFacade fachada = new ClientFacade();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== LOGIN MYHOME ===");
            System.out.print("Digite seu email (ou 'sair'): ");
            String email = scanner.nextLine().trim();

            if (email.equalsIgnoreCase("sair")) break;

            if (fachada.login(email)) {
                menuPrincipal();
                fachada.logout();
            } else {
                System.out.println("Usuário não encontrado.");
            }
        }
    }

    private static void menuPrincipal() {
        while (true) {

            boolean isAdmin = fachada.getUsuarioLogado().getAdmin();

            System.out.println("\n--- MENU ---");
            System.out.println("1. Criar Anúncio");
            if (isAdmin) {
                System.out.println("2. Moderar Anúncio (Admin)");
            }
            System.out.println("3. Comprar Anúncio");
            System.out.println("4. Configurações Usuário");
            System.out.println("0. Logout");
            System.out.print("Escolha: ");

            String op = scanner.nextLine();
            if (op.equals("0")) break;

            switch (op) {
                case "1": menuCriarAnuncio(); break;
                case "2": if (isAdmin) menuModeracao(); else System.out.println("Acesso negado."); break;
                case "3": menuComprar(); break;
                case "4": menuConfiguracoes(); break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuCriarAnuncio() {
        System.out.println("\n1. Criar anúncio do zero");
        System.out.println("2. Usar configuração");
        System.out.println("3. Criar Configuração");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();

        if (op.equals("2")) {
            System.out.println("Presets: " + fachada.getAllPresets());
            System.out.print("Chave: ");
            String chave = scanner.nextLine().toUpperCase();
            Anuncio a = fachada.getanuncioConfigByChave(chave);
            if (a != null) {
                System.out.println("Anúncio criado a partir de " + chave);
            }
            return;
        }

        AnuncioDTO adto = new AnuncioDTO();
        System.out.print("Título: "); adto.titulo = scanner.nextLine();
        System.out.print("Preço: "); adto.preco = Double.parseDouble(scanner.nextLine());
        System.out.print("Tipo (CASA/APARTAMENTO): "); adto.tipo = scanner.nextLine().toUpperCase();

        ImovelDTO idto = new ImovelDTO();
        System.out.print("Área: "); idto.area = Double.parseDouble(scanner.nextLine());
        idto.localizacao = new Double[]{0.0, 0.0}; // Simplificado
        idto.finalidade = FinalidadeEnum.VENDA;

        if (adto.tipo.equals("CASA")) {
            System.out.print("Quartos: "); idto.qtdQuartos = Integer.parseInt(scanner.nextLine());
            idto.temQuintal = true;
        } else {
            System.out.print("Andar: "); idto.andar = Integer.parseInt(scanner.nextLine());
        }

        if (op.equals("1")) {
            fachada.criarAnuncio(adto, idto);
            System.out.println("Anúncio criado!");
        } else if (op.equals("3")) {
            System.out.print("Nome da Config: ");
            String chave = scanner.nextLine().toUpperCase();
            fachada.criarConfig(chave, adto, idto);
            System.out.println("Configuração salva!");
        }
    }

    private static void menuModeracao() {
        System.out.println("\n--- PENDENTES DE MODERAÇÃO ---");
        List<Anuncio> pendentes = fachada.listarAnuncioModeracao();
        for (Anuncio a : pendentes) {
            System.out.println("[" + a.getId() + "] " + a.getTitulo());
        }

        System.out.print("ID para aprovar (ou Enter para cancelar): ");
        String id = scanner.nextLine();
        if (!id.isEmpty()) {
            fachada.aprovarAnuncio(Integer.parseInt(id));
        }
    }

    private static List<Anuncio> MenuFiltrar() {
        System.out.println("\n--- FILTRAR ANÚNCIOS ---");
    
        System.out.print("Título contém (Enter para pular): ");
        String titulo = scanner.nextLine();
        titulo = titulo.isEmpty() ? null : titulo;

        System.out.print("Preço mínimo (Enter para pular): ");
        String precoMinStr = scanner.nextLine();
        Double precoMin = precoMinStr.isEmpty() ? null : Double.parseDouble(precoMinStr);

        System.out.print("Preço máximo (Enter para pular): ");        
        String precoMaxStr = scanner.nextLine();
        Double precoMax = precoMaxStr.isEmpty() ? null : Double.parseDouble(precoMaxStr);

        ArrayList<String> tipoImovel = null;
        System.out.print("Deseja filtrar por tipo de imóvel? (s/n): ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("s")) {
            tipoImovel = new ArrayList<>();
            while (true) {
                System.out.print("Tipo (CASA/APTO) (Enter para finalizar): ");
                String tipo = scanner.nextLine().toUpperCase();
                if (tipo.isEmpty()) break;
                if (tipo.equals("CASA") || tipo.equals("APTO")) {
                    tipoImovel.add(tipo);
                    System.out.println("Tipo '" + tipo + "' adicionado.");
                } else {
                    System.out.println("Tipo inválido. Use CASA ou APTO.");
                }
            }
            if (tipoImovel.isEmpty()) tipoImovel = null;
        }

        Double[] localizacao = null;
        System.out.print("Deseja filtrar por localização? (s/n): ");
        resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Latitude: ");
            Double lat = Double.parseDouble(scanner.nextLine());
            System.out.print("Longitude: ");
            Double lon = Double.parseDouble(scanner.nextLine());
            localizacao = new Double[]{lat, lon};
        }

        Boolean temCondominio = null;
        System.out.print("Possui condomínio? (s/n/Enter para pular): ");
        String condominioStr = scanner.nextLine();
        if (condominioStr.equalsIgnoreCase("s")) {
            temCondominio = true;
        } else if (condominioStr.equalsIgnoreCase("n")) {
            temCondominio = false;
        }

        return fachada.filtrarAnuncios(titulo, precoMin, precoMax, tipoImovel, localizacao, temCondominio);
    }

    private static void menuComprar() {
        List<Anuncio> anuncios = fachada.listartodosMenosUsuarioCorrente();
        
        while (true) {
            System.out.println("\n--- ANÚNCIOS DISPONÍVEIS ---");
            for (Anuncio a : anuncios) {
                System.out.println("[" + a.getId() + "] " + a.getTitulo() + " - R$ " + a.getPreco());
            }
    
            System.out.print("Digite o ID para comprar, 'filtrar' para filtrar anúncios ou Enter para sair: ");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("filtrar")){
               anuncios = MenuFiltrar();
            }
            else if (!id.isEmpty() && id.matches("\\d+")) {
                fachada.comprarAnuncio(Integer.parseInt(id));
                System.out.println("Pedido de compra realizado para o ID " + id);
                break;
            }
            else if (id.isEmpty()) break;
            
        }
    }

    private static void menuConfiguracoes() {
        System.out.println("\n--- NOTIFICAÇÃO ---");
        System.out.println("1. Email | 2. WhatsApp | 3. SMS");
        String op = scanner.nextLine();
        switch (op) {
            case "1": fachada.setMeioDeNotificacao(new NotificacaoEmail()); break;
            case "2": fachada.setMeioDeNotificacao(new NotificacaoWhatsapp()); break;
            case "3": fachada.setMeioDeNotificacao(new NotificacaoSMS()); break;
        }
        System.out.println("Meio de notificação atualizado.");
    }
}