# 🏆🇧🇷 Brasileirão Coders - Projeto Final

Este repositório contém o projeto desenvolvido durante o módulo **Técnicas de Programação I** do curso *Santander Coders
2024.1*. A aplicação faz a leitura e processamento de dados provenientes de arquivos `.csv`, utilizando Java Streams e
outras técnicas avançadas de programação.

## ⚙️ Tecnologias Utilizadas

As seguintes tecnologias foram empregadas para o desenvolvimento do projeto:

- **☕ Java 17**: Linguagem de programação.
- **🚀 Spring Boot**: Framework para simplificar a criação de aplicações Java.
- **🌐 Thymeleaf**: Template engine para renderização de páginas HTML no lado do servidor.
- **📝 Swagger**: Ferramenta para documentação interativa da API.
- **📦 Maven**: Gerenciador de dependências e build.

## 🎯 Objetivo do Projeto

O projeto tem como objetivo aplicar conceitos e boas práticas de programação abordadas no curso, tais como:

- 🗓️ Manipulação e formatação de datas.
- 🧑‍💻 Programação funcional e uso de Streams.
- 💾 Operações com IO Stream e NIO.
- 🧩 Utilização de interfaces funcionais e Optional.
- ⚡ Implementação de programação paralela e assíncrona.

## 📡 Endpoints

### API Endpoints (`/api`)

Os endpoints abaixo respondem em formato JSON, oferecendo funcionalidades para consulta de dados relacionados a
jogadores, partidas e times.

- **⚽ GET `/api/jogadores/mais-cartoes/{colorCard}`**: Retorna uma lista dos jogadores com mais cartões de uma
  determinada cor (vermelho ou amarelo).
- **⚽ GET `/api/jogadores/mais-gols`**: Retorna os jogadores que marcaram mais gols.
- **⚽ GET `/api/jogadores/mais-gols/{typeGoals}`**: Retorna os jogadores com mais gols de um determinado tipo (gols de
  pênalti, gols de falta, etc.).
- **🏟️ GET `/api/partidas/maior-placar`**: Retorna as partidas com o maior placar de todos os tempos.
- **🏟️ GET `/api/partidas/maior-placar/{year}`**: Retorna as partidas com o maior placar em um determinado ano.
- **🏆 GET `/api/times/mais-vitorias`**: Retorna os times com mais vitórias.
- **🏆 GET `/api/times/mais-vitorias/{year}`**: Retorna os times com mais vitórias em um determinado ano.
- **🗺️ GET `/api/estados/menos-jogos?anoInicio&anoFim`**: Retorna os estados que sediaram menos jogos dentro de um
  intervalo de anos específico.

### 🌐 Web Endpoints (`/web`)

Estes endpoints são responsáveis por renderizar páginas HTML no lado do servidor.

- **⚽ GET `/web/jogadores/mais-cartoes`**: Exibe uma página com os jogadores que receberam mais cartões.
- **🏟️ GET `/web/partidas/maior-placar`**: Exibe uma página com as partidas com o maior placar.
- **🏆 GET `/web/times/mais-vitorias`**: Exibe uma página com os times com mais vitórias.

## 🛠️ Funcionalidades e Serviços

### ⚽ PlayersWithMostCardsService

Este serviço é responsável por processar os dados dos jogadores a partir de um arquivo `.csv`.

- **Arquivo lido**: `campeonato-brasileiro-cartoes.csv`
- **Função principal**: Filtrar e retornar os jogadores que receberam mais cartões de uma cor específica (vermelho ou
  amarelo).

### 🏟️ MatchesWithHighestScoreService

Este serviço lida com as informações de partidas a partir de outro arquivo `.csv`.

- **Arquivo lido**: `campeonato-brasileiro-full.csv`
- **Função principal**: Filtrar e retornar as partidas com a maior pontuação em um determinado ano.

### 🏆 TeamsWithMostWinsService

Este serviço processa os dados dos times a partir de arquivos `.csv` para identificar os times com mais vitórias.

- **Arquivo lido**: `campeonato-brasileiro-full.csv`
- **Função principal**: Filtrar e retornar os times com mais vitórias em um determinado ano ou no total.

### 🗺️ StatesWithFewestGamesService

Este serviço é responsável por retornar os estados com menos jogos sediados dentro de um intervalo de anos.

- **Arquivo lido**: `campeonato-brasileiro-estados.csv`
- **Função principal**: Filtrar e retornar os estados que sediaram menos jogos em um intervalo de anos fornecido.

## 🚀 Pontos Fortes do Projeto

- Uso extensivo de Streams para manipulação eficiente de dados.
- Implementação segura com Optional, prevenindo possíveis NullPointerExceptions.
- Utilização de Thymeleaf para renderização de páginas web dinâmicas.
- Documentação da API gerada automaticamente com Swagger.

## 📈 Pontos de Melhoria

Algumas áreas do projeto que podem ser aprimoradas:

- **🔧 Tratamento de Exceções**: Melhorar a captura e retorno de mensagens de erro mais detalhadas para os usuários.
- **🧪 Testes Automatizados**: Implementar testes unitários e de integração para garantir maior robustez do código.
- **⚡ Otimização de Leitura de Arquivos**: A leitura dos arquivos `.csv` pode ser otimizada para processar volumes
  maiores de dados com mais eficiência.

## 🛠️ Como Executar o Projeto

Siga os passos abaixo para clonar e executar o projeto localmente:

1. Clone este repositório:
   ```bash
   git clone https://github.com/dluks82/coders24-tpi-projeto-final.git
   cd coders24-tpi-projeto-final
   ```

2. Execute o projeto utilizando o Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Acesse a aplicação no navegador através do endereço:
   ```
   http://localhost:8080
   ```

   O acesso redirecionará para a página inicial do `/web`.

4. A documentação completa da API gerada pelo Swagger pode ser acessada em:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## 📜 Licença

Este projeto é distribuído sob a licença especificada no arquivo [LICENSE](LICENSE).

## 🧑‍💻 Equipe

| [<img src="https://github.com/dluks82.png" width="100" alt="Perfil do GitHub Diogo" />](https://github.com/dluks82) | [<img src="https://github.com/Isaquemz.png" width="100" alt="Perfil do GitHub Isaque" />](https://github.com/Isaquemz) | [<img src="https://github.com/RAODomingos.png" width="100" alt="Perfil do GitHub Rômulo" />](https://github.com/RAODomingos) |
|:-------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------:|
| [Diogo Oliveira](https://github.com/dluks82)                                                                        | [Isaque Menezes](https://github.com/Isaquemz)                                                                          | [Rômulo Domingos](https://github.com/RAODomingos)                                                                         |


## 💬 Agradecimentos

Gostaríamos de expressar nossa gratidão ao instrutor e colaboradores do **Santander Coders 2024.1**, cujo apoio foi
essencial para a conclusão deste projeto.
