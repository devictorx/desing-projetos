# ScreenMusic

## Apresentação e Relatório da Aplicação

### 1. Introdução
A **ScreenMusic** é uma aplicação desenvolvida para gerenciar artistas, álbuns e músicas. Criada com o objetivo de fornecer um gerenciamento eficiente e organizado no universo musical, utiliza a arquitetura **Spring Boot**, permitindo a construção de um backend robusto e escalável. O projeto visa oferecer uma interface amigável para usuários que desejam cadastrar e consultar informações musicais.

### 2. Padrões de Projeto Implementados

#### 2.1. Padrão Factory
- **Classes**: `AlbumFactory`, `ArtistaFactory`, `MusicaFactory`
- **Descrição**: Facilita a criação consistente de objetos como Álbuns, Artistas e Músicas.
- **Benefícios**:
  - Reduz a complexidade na instância de objetos.
  - Melhora a legibilidade do código.
- **Desafios**:
  - Necessidade de manter a lógica de criação centralizada.
- **Solução**: Implementação de testes unitários para garantir o funcionamento das fábricas.

#### 2.2. Padrão Observer
- **Classes**: `MusicaNotifier`, `MusicaObserver`
- **Descrição**: Permite a notificação de mudanças nas músicas, atualizando automaticamente partes da aplicação.
- **Benefícios**:
  - Desacoplamento de componentes.
  - Maior flexibilidade na adição de novos observadores.
- **Desafios**:
  - Gerenciamento da complexidade com múltiplos observadores.
- **Solução**: Limitação do número de notificações e criação de um mecanismo de registro para observadores ativos.

#### 2.3. Padrão Repository
- **Interfaces**: `AlbumRepository`, `ArtistaRepository`, `MusicaRepository`
- **Descrição**: Abstrai a interação com o banco de dados usando **Spring Data JPA**.
- **Benefícios**:
  - Simplificação das operações de CRUD.
  - Melhoria na manutenibilidade do código.
- **Desafios**:
  - Dependência da estrutura do Spring Data JPA.
- **Solução**: Desenvolvimento de testes para validar as operações do repositório.

### 3. Benefícios Gerais dos Padrões de Projeto
Os padrões implementados oferecem uma base sólida, melhorando a manutenibilidade, legibilidade e escalabilidade do código. A clara divisão de responsabilidades facilita a colaboração da equipe de desenvolvimento.

### 4. Desafios e Lições Aprendidas
Durante a implementação, enfrentamos desafios, como garantir a integridade dos dados ao adicionar músicas a álbuns e artistas existentes. A adoção do padrão Observer trouxe complexidade, mas também flexibilidade. Essa experiência reforçou a importância de aplicar padrões adequados para resolver problemas comuns. Futuramente, revisaremos a arquitetura do Observer para considerar padrões mais simples, caso a complexidade se torne excessiva.

### 5. Conclusão
A aplicação **ScreenMusic** exemplifica o uso eficaz de padrões de projeto na construção de um sistema robusto e escalável. A implementação dos padrões Factory, Observer e Repository contribuiu significativamente para a qualidade e manutenibilidade do código.

### 6. Referências
- [Documentação do Spring Boot](https://spring.io/projects/spring-boot)
- "Design Patterns: Elements of Reusable Object-Oriented Software" - Erich Gamma et al.
