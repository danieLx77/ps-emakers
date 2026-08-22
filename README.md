# ps-emakers

Sistema de gerenciamento de empréstimos de livros desenvolvido com arquitetura Spring Boot 4.0.6 seguindo padrão de camadas. A aplicação fornece APIs RESTful para cadastro e gerenciamento de pessoas e livros, com autenticação via JWT, soft delete implementation e migrações Flyway. Princípios adotidos: separação de responsabilidades entre controle, serviço e persistência, uso de DTOs para requisições/respostas, e segurança stateless baseada em tokens.

## Tecnologias e Dependências
- **Linguagem**: Java 21
- **Framework**: Spring Boot 4.0.6
- **Build Tool**: Maven 3.9.6 (wrapper mvnw)
- **Banco de Dados**: PostgreSQL (driver runtime)
- **Migrações**: Flyway Database PostgreSQL
- **Documentação API**: SpringDoc OpenAPI 3.0.2 (Swagger UI)
- **Segurança**: Spring Security com JWT (java-jwt 4.4.0), BCryptPasswordEncoder, stateless session
- **Funcionalidades**: Spring Cloud OpenFeign, Lombok, Spring Boot Devtools
- **Validação**: spring-boot-starter-validation

## Arquitetura do Sistema
```
ps-emakers/
├── src/main/java/br/com/emakers/psemakers/
│   ├── controller/      # REST controllers (PessoaController, LivroController, AuthController)
│   │   └── implements PessoaDoc, LivroDoc, AuthDoc
│   ├── service/         # Business logic (PessoaService, LivroService, AuthService)
│   ├── data/
│   │   ├── entity/      # JPA Entities (Pessoa, Livro) com soft delete
│   │   ├── repository/  # JpaRepository custom queries
│   │   ├── dto/         # Request/Response DTOs
│   │   └── enuns/       # StatusRegistro
│   ├── exception/       # Global exception handlers
│   ├── infra/           # SecurityFilter, SecurityConfig, OpenApiConfig
│   └── main/            # PsEmakersApplication.java (@SpringBootApplication)
│   └── docs/            # Interfaces de documentação (PessoaDoc, LivroDoc, AuthDoc)
├── src/main/resources/
│   ├── application.properties  # Configuração datasource, Flyway, JPA
│   └── db/migration/           # Flyway SQL migrations (V1-V4)
├── .env                         # Variáveis de ambiente (DB_USERNAME, DB_PASSWORD, SECRET)
├── pom.xml                      # Maven build configuration (parent: spring-boot-starter-parent 4.0.6)
├── docker-compose.yml           # PostgreSQL 16-alpine + API service
└── Dockerfile                   # Multi-stage: maven build → eclipse-temurin 21-jre runtime
```

## Pré-requisitos
- **JDK 21** ou OpenJDK 21
- **Maven 3.9.6** (ou uso do wrapper `mvnw`)
- **PostgreSQL 16** rodando localmente ou via Docker Compose
- **Docker** e **Docker Compose** (opcional, para ambiente isolado)
- Variáveis de ambiente: `DB_USERNAME`, `DB_PASSWORD`, `SECRET`

## Instalação e Configuração
1. **Clonagem do repositório**: `git clone <url-do-repositorio>` e entrar na pasta `ps-emakers`
2. **Configuração de variáveis de ambiente**: Copiar `.env` como modelo e preencher as variáveis `DB_USERNAME`, `DB_PASSWORD` e `SECRET` (segredo JWT). Alternativamente, configurar essas variáveis no sistema operacional ou no Docker Compose.
3. **Instalação de dependências e build**: `./mvnw clean package -DskipTests` ou `mvn clean package -DskipTests`

## Execução
- **Ambiente de desenvolvimento**: `./mvnw spring-boot:run` (porta 8080 padrão)
- **Via Docker Compose**: `docker-compose up -db` (inicará PostgreSQL e a API)
- **Build gerado**: O Dockerfile gera um JAR na etapa final; executável via `java -jar target/*.jar`

## Testes Automatizados
- **Testes unitários/integração**: `./mvnw test` ou `mvn test`
- **Cobertura de código**: Pode ser gerada via `./mvnw jacoco:report`
- **Flyway migrations**: `./mvnw flyway:migrate` para aplicar migrações; `./mvnw flyway:info` para status

## Endpoints da API
| Método HTTP | Rota | Parâmetros | Descrição |
|-------------|------|------------|-----------|
| POST | /auth/login | body: AuthRequest (email, senha) | Login - retorna JWT token (público) |
| POST | /pessoas | body: PessoaRequest | Cadastro de pessoa (público) |
| GET | /pessoas | - | Listar todas as pessoas ativas (exige JWT) |
| GET | /pessoas/{id} | path: id (Long) | Buscar pessoa por ID (exige JWT) |
| PUT | /pessoas/{id} | path: id; body: PessoaRequest | Atualizar pessoa (exige JWT) |
| DELETE | /pessoas/{id} | path: id | Inativar pessoa (soft delete) (exige JWT) |
| PATCH | /pessoas/{idPessoa}/emprestar/{idLivro} | path: idPessoa, idLivro | Emprestar livro (exige JWT) |
| PATCH | /pessoas/{idPessoa}/devolver/{idLivro} | path: idPessoa, idLivro | Devolver livro (exige JWT) |
| POST | /livros | body: LivroRequest | Cadastro de livro (exige JWT) |
| GET | /livros | - | Listar livros ativos (exige JWT) |
| GET | /livros/{id} | path: id (Long) | Buscar livro por ID (exige JWT) |
| PUT | /livros/{id} | path: id; body: LivroRequest | Atualizar livro (exige JWT) |
| DELETE | /livros/{id} | path: id | Inativar livro (soft delete) (exige JWT) |

