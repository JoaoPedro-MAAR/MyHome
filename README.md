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

---

## Padrões de Projeto Utilizados

### Requisito 1 – Builder

- **Padrão:** Builder  
- **Uso:**  
  > (Descrever aqui como o Builder é usado para construir objetos complexos, por exemplo, anúncios ou imóveis com muitos atributos opcionais.)

---

### Requisito 2 – Prototype

- **Padrão:** Prototype  
- **Uso:**  
  > (Descrever aqui como o Prototype é usado para clonar configurações padrão de anúncios ou imóveis, criando instâncias a partir de protótipos.)

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
  > (Descrever aqui como uma fachada simplifica o acesso às funcionalidades principais do sistema – por exemplo, criação de anúncios, busca avançada, moderação e geração de contratos – expondo uma API mais simples para a camada de apresentação.)

---

## Como Executar o Projeto

1. Certifique-se de ter o Java devidamente instalado e configurado (JDK compatível com o projeto).  
2. Importe o projeto na sua IDE de preferência ou compile pelo terminal.  
3. No arquivo configuracao.properties, defina o caminho para popular os Anuncios e Usuários nas variáveis ``caminhoUsuarioCSV`` e `caminhoAnuncioCSV`.
4. No mesmo arquivo, definir a pasta de destino dos contratos através da variável `caminhoContratos`.
5. Localize a classe `Main` no caminho:

   ```text
   src\main\java\br\com\edu\ifpb\pps\Main.java

6. Execute a aplicação a partir da classe `Main`.
