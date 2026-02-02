package br.com.edu.ifpb.pps;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.Facade.ClientFacade;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.notificacao.NotificacaoEmail;
import br.com.edu.ifpb.pps.notificacao.NotificacaoSMS;
import br.com.edu.ifpb.pps.notificacao.NotificacaoWhatsapp;
import br.com.edu.ifpb.pps.observador.ObservadorLog;
import br.com.edu.ifpb.pps.observador.ObservadorUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainInterativo {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClientFacade fachada = new ClientFacade();

    public static void main(String[] args) {
        System.out.println("=== BEM-VINDO AO MYHOME ===");

        while (true) {
            System.out.println("\n--- TELA DE LOGIN ---");
            System.out.print("Digite seu e-mail para entrar (ou 'sair' para fechar): ");
            String email = scanner.nextLine().trim();

            if (email.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando sistema...");
                break;
            }

            if (fachada.login(email)) {
                System.out.println("Login realizado com sucesso! Olá, " + fachada.getUsuarioLogado().getNome());

                // Configuração inicial de notificação padrão (pode ser alterada no menu)
                fachada.setMeioDeNotificacao(new NotificacaoEmail());

                exibirMenuPrincipal();
                fachada.logout();
            } else {
                System.out.println("ERRO: Usuário não encontrado no banco de dados.");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        while (true) {
            boolean isAdmin = fachada.getUsuarioLogado().getAdmin();

            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Criar Anúncio");
            if (isAdmin) {
                System.out.println("2. Moderar Anúncio (Área Admin)");
            }
            System.out.println("3. Comprar Anúncio");
            System.out.println("4. Configurações Usuário");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    menuCriarAnuncio();
                    break;
                case "2":
                    if (isAdmin) {
                        menuModeracao();
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case "3":
                    menuComprar();
                    break;
                case "4":
                    menuConfiguracoes();
                    break;
                case "0":
                    return; // Sai do loop e faz logout
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    // ================== 1. MENU CRIAR ANÚNCIO ==================
    private static void menuCriarAnuncio() {
        System.out.println("\n--- CRIAR ANÚNCIO ---");
        System.out.println("1. Criar anúncio do zero");
        System.out.println("2. Usar configuração (Preset)");
        System.out.println("3. Criar Configuração");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();

        switch (op) {
            case "1":
                criarAnuncioDoZero();
                break;
            case "2":
                usarConfiguracaoPreset();
                break;
            case "3":
                criarNovaConfiguracao();
                break;
            case "0":
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void criarAnuncioDoZero() {
        System.out.println("\n>> Novo Anúncio");
        AnuncioDTO dtoAnuncio = lerDadosBasicosAnuncio();
        ImovelDTO dtoImovel = lerDadosImovel(dtoAnuncio.tipo);

        fachada.criarAnuncio(dtoAnuncio, dtoImovel);
        System.out.println("Sucesso! Anúncio criado e enviado para rascunho.");

        // Adiciona observadores automaticamente para vermos o fluxo funcionar
        adicionarObservadoresAutomaticamente(dtoAnuncio.titulo);
    }

    private static void usarConfiguracaoPreset() {
        System.out.println("\n>> Usar Preset");
        List<String> presets = fachada.getAllPresets();
        if (presets.isEmpty()) {
            System.out.println("Nenhum preset disponível.");
            return;
        }
        System.out.println("Presets disponíveis: " + presets);
        System.out.print("Digite o nome exato do preset (Chave): ");
        String chave = scanner.nextLine().toUpperCase();

        // DTO apenas com as diferenças (Padrão Prototype oculto na fachada)
        AnuncioDTO diffDTO = new AnuncioDTO();
        System.out.print("Novo Título: ");
        diffDTO.titulo = scanner.nextLine();
        System.out.print("Novo Preço: ");
        diffDTO.preco = lerDouble();

        fachada.criarAnuncioApartirDePreset(chave, diffDTO);
        System.out.println("Sucesso! Anúncio criado a partir do preset '" + chave + "'.");

        adicionarObservadoresAutomaticamente(diffDTO.titulo);
    }

    private static void criarNovaConfiguracao() {
        System.out.println("\n>> Nova Configuração (Preset)");
        System.out.print("Nome da Configuração (Chave): ");
        String chave = scanner.nextLine().toUpperCase();

        AnuncioDTO dtoAnuncio = lerDadosBasicosAnuncio();
        ImovelDTO dtoImovel = lerDadosImovel(dtoAnuncio.tipo);

        fachada.criarConfig(chave, dtoAnuncio, dtoImovel);
        System.out.println("Configuração '" + chave + "' salva com sucesso!");
    }

    // ================== 2. MENU MODERAÇÃO (ADMIN) ==================
    private static void menuModeracao() {
        System.out.println("\n--- MODERAÇÃO DE ANÚNCIOS ---");
        System.out.println("1. Listar pendentes de moderação");
        System.out.println("2. Filtrar anúncios");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();

        switch (op) {
            case "1":
                moderarPendentes();
                break;
            case "2":
                filtrarAnunciosGeral();
                break;
            case "0":
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void moderarPendentes() {
        List<Anuncio> pendentes = fachada.listarAnuncioModeracao();
        if (pendentes.isEmpty()) {
            System.out.println("Nenhum anúncio pendente de moderação.");
            return;
        }

        System.out.println("\n--- Lista de Pendentes ---");
        for (Anuncio a : pendentes) {
            System.out.println("ID: " + a.getId() + " | Título: " + a.getTitulo() + " | Preço: " + a.getPreco() + " | Anunciante: " + a.getAnunciante().getNome());
        }

        System.out.print("\nDigite o ID do anúncio para Processar Moderação (ou Enter para voltar): ");
        String inputId = scanner.nextLine();

        if (!inputId.isEmpty()) {
            try {
                Integer id = Integer.parseInt(inputId);
                System.out.println("Processando cadeia de moderação...");
                boolean aprovado = fachada.processarModeracao(id); // Usa a Facade refatorada

                if (aprovado) {
                    System.out.println("RESULTADO: APROVADO! O anúncio agora está Ativo.");
                } else {
                    System.out.println("RESULTADO: REPROVADO! O anúncio foi Suspenso (Preço baixo, termos proibidos ou sem anunciante).");
                }
            } catch (NumberFormatException e) {
                System.out.println("ID inválido.");
            }
        }
    }

    // ================== 3. MENU COMPRAR ==================
    private static void menuComprar() {
        System.out.println("\n--- COMPRAR ANÚNCIO ---");
        System.out.println("1. Listar anúncios disponíveis");
        System.out.println("2. Filtrar anúncios");
        System.out.println("3. Comprar anúncio por ID");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();

        switch (op) {
            case "1":
                listarDisponiveisCompra();
                break;
            case "2":
                filtrarAnunciosGeral();
                break;
            case "3":
                realizarCompra();
                break;
            case "0":
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listarDisponiveisCompra() {
        // Assume-se que 'listartodosMenosUsuarioCorrente' traz os disponíveis
        // Mas idealmente filtraríamos apenas os com estado "Ativo"
        List<Anuncio> todos = fachada.listarAnuncioAtivos();
        System.out.println("\n--- Anúncios Disponíveis (Exceto seus) ---");
        boolean encontrou = false;
        for (Anuncio a : todos) {
            if (a.getEstado().getNome().equalsIgnoreCase("Ativo")) {
                System.out.println("ID: " + a.getId() + " | " + a.getTitulo() + " | R$ " + a.getPreco() + " | Tipo: " + a.getImovel().getClass().getSimpleName());
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum anúncio ATIVO encontrado.");
    }

    private static void realizarCompra() {
        System.out.print("Digite o ID do anúncio que deseja comprar: ");
        String inputId = scanner.nextLine();
        try {
            Integer id = Integer.parseInt(inputId);
            Anuncio a = fachada.buscarPorId(id);

            if (a != null) {
                if (!a.getEstado().getNome().equalsIgnoreCase("Ativo")) {
                    System.out.println("Este anúncio não está ativo para venda (Estado atual: " + a.getEstado().getNome() + ").");
                    return;
                }
                if (a.getAnunciante().getEmail().equals(fachada.getUsuarioLogado().getEmail())) {
                    System.out.println("Você não pode comprar seu próprio anúncio.");
                    return;
                }

                fachada.comprarAnuncio(id);
                System.out.println("COMPRA REALIZADA COM SUCESSO!");
                System.out.println("O contrato foi gerado na pasta 'contratos'.");
            } else {
                System.out.println("Anúncio não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    // ================== FILTROS (Compartilhado) ==================
    private static void filtrarAnunciosGeral() {
        System.out.println("\n>> Filtrar Anúncios");
        System.out.print("Título contém (Enter para pular): ");
        String titulo = scanner.nextLine(); if(titulo.isEmpty()) titulo = null;

        System.out.print("Preço Mínimo (Enter para pular): ");
        Double min = lerDoubleOpcional();

        System.out.print("Preço Máximo (Enter para pular): ");
        Double max = lerDoubleOpcional();

        ArrayList<String> tipos = null;
        System.out.print("Filtrar por tipo? (CASA/APARTAMENTO) (Enter para pular): ");
        String tipoStr = scanner.nextLine().toUpperCase();
        if (!tipoStr.isEmpty()) {
            tipos = new ArrayList<>();
            if(tipoStr.contains("CASA")) tipos.add("CASA");
            if(tipoStr.contains("APT") || tipoStr.contains("APARTAMENTO")) tipos.add("APARTAMENTO");
        }

        List<Anuncio> resultados = fachada.filtrarAnuncios(titulo, min, max, tipos, null, null);

        System.out.println("\n--- Resultados do Filtro ---");
        for (Anuncio a : resultados) {
            System.out.println("ID: " + a.getId() + " | " + a.getTitulo() + " | R$ " + a.getPreco() + " | Estado: " + a.getEstado().getNome());
        }
    }

    // ================== 4. CONFIGURAÇÕES DO USUÁRIO ==================
    private static void menuConfiguracoes() {
        System.out.println("\n--- CONFIGURAÇÕES DE USUÁRIO ---");
        System.out.println("1. Mudar estratégia de notificação");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        String op = scanner.nextLine();

        if (op.equals("1")) {
            System.out.println("Escolha o meio:");
            System.out.println("1. E-mail");
            System.out.println("2. WhatsApp");
            System.out.println("3. SMS");
            System.out.print("Opção: ");
            String meio = scanner.nextLine();

            switch (meio) {
                case "1": fachada.setMeioDeNotificacao(new NotificacaoEmail()); System.out.println("Definido para Email."); break;
                case "2": fachada.setMeioDeNotificacao(new NotificacaoWhatsapp()); System.out.println("Definido para WhatsApp."); break;
                case "3": fachada.setMeioDeNotificacao(new NotificacaoSMS()); System.out.println("Definido para SMS."); break;
                default: System.out.println("Inválido.");
            }
        }
    }

    // ================== MÉTODOS AUXILIARES ==================

    private static AnuncioDTO lerDadosBasicosAnuncio() {
        AnuncioDTO dto = new AnuncioDTO();
        System.out.print("Título: ");
        dto.titulo = scanner.nextLine();
        System.out.print("Preço: ");
        dto.preco = lerDouble();
        System.out.print("Tipo (CASA/APARTAMENTO): ");
        dto.tipo = scanner.nextLine().toUpperCase();
        return dto;
    }

    private static ImovelDTO lerDadosImovel(String tipo) {
        ImovelDTO dto = new ImovelDTO();
        System.out.print("Área (m2): ");
        dto.area = lerDouble();

        System.out.print("Finalidade (VENDA/ALUGUEL): ");
        String fin = scanner.nextLine().toUpperCase();
        dto.finalidade = fin.equals("ALUGUEL") ? FinalidadeEnum.ALUGUEL : FinalidadeEnum.VENDA;

        dto.localizacao = new Double[]{0.0, 0.0}; // Simplificado para o menu

        if ("CASA".equals(tipo)) {
            System.out.print("Qtd Quartos: ");
            dto.qtdQuartos = lerInt();
            System.out.print("Tem Quintal? (s/n): ");
            dto.temQuintal = scanner.nextLine().equalsIgnoreCase("s");
            System.out.print("Tem Jardim? (s/n): ");
            dto.temJardim = scanner.nextLine().equalsIgnoreCase("s");
        } else if ("APARTAMENTO".equals(tipo)) {
            System.out.print("Andar: ");
            dto.andar = lerInt();
            System.out.print("Tem Elevador? (s/n): ");
            dto.temElevador = scanner.nextLine().equalsIgnoreCase("s");
            System.out.print("Tem Condomínio? (s/n): ");
            dto.temCondominio = scanner.nextLine().equalsIgnoreCase("s");
        }
        return dto;
    }

    private static Double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Digite novamente: ");
            }
        }
    }

    private static Double lerDoubleOpcional() {
        String s = scanner.nextLine();
        if (s.isEmpty()) return null;
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }
    }

    private static Integer lerInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Número inteiro inválido. Digite novamente: ");
            }
        }
    }

    // Método utilitário para "colar" observadores assim que cria,
    // já que o menu não tem um passo explícito para isso mas o sistema precisa.
    private static void adicionarObservadoresAutomaticamente(String tituloAnuncio) {
        Anuncio a = fachada.buscarAnuncioTitulo(tituloAnuncio);
        if (a != null) {
            fachada.adicionarObservador(a.getId(), new ObservadorLog());
            fachada.adicionarObservador(a.getId(), new ObservadorUsuario());
        }
    }
}