# MyHome – Plataforma de Classificados de Imóveis

Projeto da disciplina de **Padrões de Projeto** do curso de **Sistemas para Internet**, 5º período, com foco em uma plataforma de classificados imobiliários capaz de cadastrar, moderar, pesquisar e notificar sobre anúncios de imóveis para venda e aluguel.

---

## Informações do Projeto

- **Disciplina:** Padrões de Projeto  
- **Período:** 5º período  
- **Professor:** Alex Sandro da Cunha Rêgo  
- **Equipe:**  
  - Andrey Vasconcelos Coutinho 
  - João Pedro Marques de Araujo 
  - Lucas Emanuel de Araujo Toscano  

---

## Visão Geral

O **MyHome** é uma plataforma de classificados de imóveis que conecta proprietários, corretores, imobiliárias e potenciais compradores/inquilinos.  
O sistema permite:

- Cadastro de anúncios de diferentes tipos de imóveis (casa, apartamento, terreno, sala comercial, galpão etc.).  
- Moderação e publicação de anúncios com regras dinâmicas.  
- Notificação de usuários por diferentes canais.  
- Busca avançada com múltiplos filtros combináveis.  
- Geração de contratos padronizados (por exemplo, aluguel e venda).

O foco do projeto é demonstrar a aplicação de múltiplos padrões de projeto para atender aos requisitos funcionais e não funcionais da solução.

---

## Classes Principais

### Anuncio

- Representa um anúncio de imóvel na plataforma, contendo informações como título, preço, anunciante, imóvel associado e estado atual. Implementa o padrão Prototype para clonagem e Observer para notificar mudanças de estado.

### Imóvel

- Classe abstrata que define a estrutura base de um imóvel (área, localização, finalidade). Serve como superclasse para tipos específicos como Casa e Apartamento. Implementa Prototype e aceita visitantes através do padrão Visitor.

### Usuário 

- Representa um usuário do sistema (anunciante, comprador ou administrador), armazenando dados pessoais (nome, email) e gerenciando o meio de notificação preferido através do padrão Strategy.

### Configuração

- Singleton responsável por carregar e fornecer acesso centralizado às configurações do sistema a partir do arquivo ``configuracao.properties``, como termos proibidos, taxa de comissão e caminhos de arquivos.

### Prototype:

- Interface genérica que define o método ``copy()`` para clonagem de objetos, utilizada por Anuncio e Imovel para criar cópias independentes de instâncias existentes.

### Banco

- Singleton que atua como repositório em memória, gerenciando as coleções de usuários e anúncios do sistema. Carrega dados iniciais via ImportCSV e fornece operações de CRUD e busca.

---

## Padrões de Projeto Utilizados

### Requisito 1 – Builder

- **Padrão:** Builder  
- **Uso:**  
  >O padrão Builder foi implementado com múltiplos Diretores especializados, um para cada tipo de imóvel (Casa e Apartamento). Cada Diretor possui o método criarComDados, que recebe um DTO unificado contendo todas as características possíveis. O papel do Diretor é filtrar esse DTO e acionar apenas os métodos do Builder pertinentes àquele tipo específico de imóvel.


---

### Requisito 2 – Prototype

- **Padrão:** Prototype  
- **Uso:**  
  >A interface Prototype foi implementada pelas classes Anuncio e Imovel para viabilizar a clonagem de objetos. Essa estrutura atende ao requisito de criação de anúncios baseados em modelos (RF02), gerenciados pela classe AnuncioRegistry. O Registry centraliza os presets, permitindo que o usuário recupere configurações existentes ou salve novas definições para uso futuro

---

### Requisito 3 – Chain of Responsibility

- **Padrão:** Chain of Responsibility  
- **Uso:**  
  > Utilizado para para implementar uma sequência de verificações seguindo as regras da moderação. O fluxo é composto de regras independentes, cada classe gerenciando uma regra especifica (ModeracaoAnunciante, ModeracaoPreco e ModeracaoTermo). O padrão permite gerenciar regras de validação e verificação de anúncio sem alterar a classe anúncio.

---

### Requisito 4 – State

- **Padrão:** State  
- **Uso:**  
  >  Aplicado para gerenciar o ciclo de vida do anúncio, desde a sua criação ao seu "fim", controlando as transições e comportamento de estados do anúncio. Cada estado é uma classe especifica que implementa a interface EstadoAnuncio, mantendo a lógica de quais ações são permitidas para cada estágio, eliminando estruturas condicionais grandes.

---

### Requisito 5 – Strategy + Observer

- **Padrões:** Strategy, Observer  
- **Uso:**  
  > A escolha de Observer foi para suprir a necessidade de alertar os observadores (Usuário e LOG) da classe anúncio, informando sempre que uma transição ocorre. O Strategy foi escolhido para permitir que o usuario consiga escolher o seu canal de notificação de preferência, podendo escolher entre Email, SMS e etc.

---

### Requisito 6 – Composite + Visitor

- **Padrões:** Composite, Visitor  
- **Uso:**  
  > Para realizar a filtragem de anúncios, foi criado uma hierarquia de filtros usando o Padrão Composite, em que existe 2 tipos de Container: FiltroCompositeAND que torna obrigatório que o Anúncio esteja de acordo com TODOS os filtros para ser aceito; e FiltroCompositeOR, que requer que o Anuncio cumpra pelo menos 1 dos filtros para que já seja aceito. Cada um desses Composites carrega uma lista de folhas. As classes de Folha do Padrão são os filtros que de fato vão ser aplicados. A folha recebe a lista de Anuncios e retorna somente aqueles que forem aceitos pelo seu parâmetro.

  > Para a situação de filtros que só funcionam com certos tipos de Imóveis, foi utilizado o padrão Visitor, em que a Folha chama a Classe Visitante, a qual tem consciencia do tipo de Imóvel de cada objeto da lista, podendo excluir do resultado o Imóvel caso ele seja incompatível com o filtro, ou aplicando o filtro seguindo as características únicas do tipo do Imóvel.

---

### Requisito 7 – Singleton

- **Padrão:** Singleton  
- **Uso:**  
  > Implementado para garantir uma única instância global que gerencia as definições do sistema, carregando de forma centralizada informações e configurações importantes, como a lista de termos proibidas para a moderação, informações necessárias para realizar envios de email e etc.
  > O padrão singleton também foi usado para a classe Banco que guarda somente uma instancia dela mesma

---

### Requisito 8 – Template Method

- **Contexto:** Fluxo de Geração de Contratos: Quando um pagamento for realizado, o sistema deve gerar contratos de Aluguel ou de Venda, dependendo da finalidade do Imóvel. Ambos seguem os mesmos passos: (1) Validar dados, (2) Gerar cláusulas padrão, (3) Inserir cláusulas específicas e (4) Gerar Arquivo. O sistema deve ser flexível para permitir futuros Tipos de Contratos.
- **Padrão:** Template Method  
- **Uso:**  
  > Para a padronização do Contrato, podendo implementar certas partes de acordo com o Tipo de Contrato, foi utilizado o padrão Template Method. Foi criado a classe abstrata GeradorContrato com toda a estrutura base que o contrato deve possuir segundo o requisito. Dado a Estrutura, cada tipo de Contrato pode herdar do abstrato e implementar as partes específicas que o diz respeito, como a validação de dados que aquele Contrato específico utiliza ou adição de cláusulas que só existem para aquele tipo de contrato. Por fim, é gerado um arquivo .txt do contrato que irá para o caminho especificado no arquivo de configurações.

---

### Outros Padrões – Facade

- **Padrão:** Facade  
- **Uso:**  
  > "O padrão Facade foi implementado naturalmente à medida que a complexidade do sistema aumentou. Essa complexidade foi simplificada para o cliente final, que interage apenas com uma Fachada Principal (ClientFacade). Além disso, dentro dessa fachada principal, foi encapsulada uma Fachada de Usuário (UserFacade), especializada em gerenciar o login e o estado da sessão.


- **Padrão:** Simple Factory
- **Uso:**
  > Foi implementado naturalmente visto que a Fachada enfrentava um problema, como gerar Imoveis sem ter que instanciar os Diretores e Builders
  > A fabrica simples, surge com esse proposito de retirar a condicional da fachada ou da main, para uma camada mais interior da aplicação, com essa
  > solução, a fachada não precisou fazer Sobrecarga de metodos para tipo de imovel. Agora para adicionar um novo tipo de Imovel será necessario: Criar
  > seu Builder e respectivo diretor e registar um novo tipo no TipoRegistry e adicionar uma nova condicional para a Fabrica Simples, isso evita que teriamos que mexer 
  > em camadas mais externas da aplicação. 
---


## Estrutura dos dados dos arquivos CSV
O sistema utiliza arquivos CSV para popular o banco de dados inicial (Seed). Os arquivos devem estar localizados na pasta configurada no configuracao.properties (padrão: dados/), utilizar ponto e vírgula (;) como separador e não devem conter espaços em branco nas linhas vazias.

- 1. users.csv (Usuários)
   A primeira linha é considerada cabeçalho e será ignorada. Formato: email;nome;isAdmin

      + email (String): O e-mail do usuário (chave de login).

       + nome (String): Nome completo do usuário.

      + isAdmin (Boolean): true para administrador, false para usuário comum. (Opcional, padrão é false).
- 2. anuncio.csv (Anúncios)
        A primeira linha é considerada cabeçalho e será ignorada. O formato dos campos varia ligeiramente dependendo do TIPO do imóvel (primeira coluna).

  + Campos Comuns (Colunas 0 a 7):
  + TIPO;TITULO;PRECO;EMAIL_ANUNCIANTE;AREA;LATITUDE;LONGITUDE;FINALIDADE;...

  + TIPO: CASA ou APARTAMENTO.
  + TITULO: Título do anúncio.
  +   PRECO: Valor do imóvel (Double).
  + EMAIL_ANUNCIANTE: Deve corresponder a um email existente no users.csv.
  
  +   AREA: Área em m² (Double).

  + LATITUDE: Coordenada geográfica (Double).
  
  + LONGITUDE: Coordenada geográfica (Double).

  + FINALIDADE: VENDA ou ALUGUEL.

+  Campos Específicos por Tipo:
+ Para CASA: ...;QUARTOS;TEM_QUINTAL;TEM_JARDIM

  + QUARTOS (Int): Quantidade de quartos.

  + TEM_QUINTAL (Boolean): true ou false.

  + TEM_JARDIM (Boolean): true ou false.

+ Para APARTAMENTO: ...;ANDAR;TEM_ELEVADOR;TEM_CONDOMINIO

  + ANDAR (Int): Número do andar.

  + TEM_ELEVADOR (Boolean): true ou false.

  + TEM_CONDOMINIO (Boolean): true ou false.

## Como Executar o Projeto

1. **Pré-requisitos**: Certifique-se de ter o **Java 21** (ou superior) devidamente instalado e configurado.
2. **Importação**: Importe o projeto na sua IDE de preferência (IntelliJ, Eclipse, VS Code) como um projeto Maven/Gradle ou projeto Java padrão.
3. **Maven**: Rode o build do Maven
3. **Configuração do Ambiente**:
   Crie um arquivo chamado `configuracao.properties` na **raiz do projeto** (fora da pasta `src`). Adicione as seguintes propriedades para configurar os caminhos e parâmetros do sistema:
```properties
# Caminhos para carga inicial de dados (CSV)
caminhoUsuarioCSV=dados/users.csv
caminhoAnuncioCSV=dados/anuncio.csv

# Pasta onde os contratos gerados (TXT) serão salvos
caminhoContratos=contratos

# Configurações de Moderação
termosProibidos=fraude,golpe,ruim,ilegal,roubo

# Configuração de Notificação por Email (Opcional - Necessário para NotificacaoEmail)
email=seu_email@gmail.com
senha=sua_senha_de_app

# Arquivo de Log
arquivoLog=myhomeLog.txt

```


4. **Preparação dos Dados**:
* Crie uma pasta chamada `dados` na raiz do projeto.
* Insira os arquivos `users.csv` e `anuncio.csv` dentro dessa pasta (conforme configurado acima) para que o sistema possa popular o banco inicial corretamente.
* Certifique-se de que a pasta `contratos` exista ou permita que o sistema a crie.


5. **Execução**:
   Localize a classe principal `main` no seguinte caminho:
```text
src/main/java/br/com/edu/ifpb/pps/main.java

```


6. **Rodar a Aplicação**:
   Clique com o botão direito na classe `main` e selecione **"Run 'main.main()'"**.
* **Login**: Utilize o e-mail `araujo.aragao@academico.ifpb.edu.br` (ou outro presente no seu CSV) para acessar o sistema.