## ⚔️ Java of Empires - Projeto de Programação Orientada a Objetos

Este projeto é uma implementação do jogo "Java of Empires", desenvolvido como parte da disciplina de **Programação Orientada a Objetos (POO)**. O objetivo foi aplicar e consolidar conceitos avançados de POO, como herança, polimorfismo e interfaces, para criar um simulador de estratégia em tempo real robusto e funcional.

-----

## 🚀 Funcionalidades Implementadas

O projeto foi estruturado para atender a todos os requisitos do escopo, garantindo uma arquitetura de código clara e uma experiência de jogo completa em termos de mecânicas.

### 1\. Arquitetura Orientada a Objetos

* **Herança e Polimorfismo:** A classe abstrata `Personagem` serve como base para todos os tipos de unidades (`Aldeão`, `Arqueiro`, `Cavaleiro`), centralizando o estado comum (vida, posição, velocidade).
* **Interfaces para Herança Múltipla de Tipo:** Foram criadas interfaces como `Guerreiro`, `Coletador` e `ComMontaria` para definir capacidades específicas. Isso permite que um personagem implemente múltiplos papéis (ex: o `Aldeão` é `Guerreiro` e `Coletador`).

### 2\. Gerenciamento de Unidades e Combate

* **Criação de Unidades:** Botões de criação implementados para todos os tipos de personagens, adicionando-os dinamicamente ao mapa.
* **Sistema de Ataque Completo:**
    * Botão **"Atacar"** funcional para todos os personagens que implementam a interface `Guerreiro`.
    * Dano aplicado com base no atributo `ataque` de cada unidade.
    * O ataque é simultâneo contra todos os inimigos dentro do alcance.
* **Alcance de Ataque Diferenciado:** Cada unidade possui um `alcanceAtaque` distinto:
    * `Aldeão`: 50px
    * `Arqueiro`: 150px
    * `Cavaleiro`: 75px
    * O dano é aplicado somente se a distância entre as unidades for $\le$ ao alcance.
* **Efeito Visual de Ataque:** Implementada a troca de sprite para simular a animação de ataque, conforme solicitado.
* **Sistema de Esquiva (Dodge):**
    * Cada personagem possui um atributo `chanceEsquiva` (ex: Aldeão: 10%, Arqueiro: 25%, Cavaleiro: 15%).
    * Utiliza a função `Random` para determinar se o ataque será esquivado, e, em caso positivo, o dano não é aplicado.
* **Remoção de Unidades Mortas:**
    * Verificação da vida após cada ataque ($vida \le 0$).
    * O personagem é removido da coleção, suas referências são limpas, e um contador de baixas por tipo é mantido e exibido no terminal.

### 3\. Interface e Controles

* **Filtros de Seleção por Tipo:** **Radio buttons** permitem filtrar os comandos de movimento e ataque por tipo de unidade ("Todos", "Aldeão", "Arqueiro", "Cavaleiro"), utilizando o operador `instanceof` para a filtragem.
* **Barra de Vida Visual:** Um retângulo colorido é desenhado acima de cada personagem, com largura proporcional à vida atual. A cor muda dinamicamente de acordo com a porcentagem de vida (verde, amarelo, vermelho).
* **Sistema de Coleta de Recursos:**
    * Botão **"Coletar"** funcional.
    * Recursos (`COMIDA`, `OURO`, `MADEIRA`) são desenhados no mapa.
    * Apenas personagens que implementam a interface `Coletador` (o `Aldeão`) podem realizar a coleta, verificando a proximidade com o recurso.

### 4\. Configuração e Manutenção

* **Centralização de Constantes:** Criada a classe `Constantes` para centralizar todos os valores de *hardcode* (vida inicial, ataque, velocidade, alcance). Isso facilita o balanceamento e a manutenção do jogo.

-----

## ⚠️ Erro de Execução Atual

Embora todas as funcionalidades tenham sido implementadas, o projeto está enfrentando um problema de execução que impede a abertura correta da janela do jogo.

O *build* do Gradle é bem-sucedido, mas a aplicação falha ao tentar iniciar a interface gráfica :(.


-----

## ⚙️ Como Executar

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/sergio-prolo-class/projeto-2-ana-ronzani.git
    ```
2.  **Navegue até o diretório raiz do projeto:**
    ```bash
    cd projeto-2-ana-ronzani
    ```
3.  **Execute o build:**
    ```bash
    ./gradlew build
    ```
4.  **Execute a aplicação:**
    ```bash
    ./gradlew run
    ```
