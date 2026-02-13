# 🚀 Padrões de Projeto com Spring Boot

[![Java](https://img.shields.io/badge/Java-11-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

## 📋 Sobre o Projeto

Projeto de demonstração da implementação prática de padrões de projeto (Design Patterns) utilizando o ecossistema Spring Framework.

O sistema consiste em uma **API RESTful** para gerenciamento de clientes com integração automática à API do **ViaCEP** para busca de endereços, demonstrando os seguintes padrões de projeto:

### 🎯 Padrões de Projeto Implementados

1. **Singleton**: Gerenciado pelo Spring através da injeção de dependências (@Autowired)
2. **Strategy/Repository**: Implementado através das interfaces de serviço e repositórios do Spring Data JPA
3. **Facade**: O Controller abstrai toda a complexidade das integrações (Banco H2 + API ViaCEP)

## 🛠️ Tecnologias Utilizadas

- **Java 11**
- **Spring Boot 2.5.4**
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **Spring Cloud OpenFeign** - Cliente HTTP declarativo
- **H2 Database** - Banco de dados em memória
- **Bean Validation** - Validação de dados
- **SpringDoc OpenAPI (Swagger)** - Documentação da API
- **Maven** - Gerenciamento de dependências

## 📦 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── one/digitalinnovation/gof/
│   │       ├── Application.java                 # Classe principal
│   │       ├── config/
│   │       │   └── SwaggerConfig.java           # Configuração do Swagger
│   │       ├── controller/
│   │       │   └── ClienteRestController.java   # Endpoints REST
│   │       ├── exception/
│   │       │   └── GlobalExceptionHandler.java  # Tratamento de exceções
│   │       ├── model/
│   │       │   ├── Cliente.java                 # Entidade Cliente
│   │       │   ├── ClienteRepository.java       # Repositório Cliente
│   │       │   ├── Endereco.java                # Entidade Endereço
│   │       │   └── EnderecoRepository.java      # Repositório Endereço
│   │       └── service/
│   │           ├── ClienteService.java          # Interface do serviço
│   │           ├── ViaCepService.java           # Cliente Feign ViaCEP
│   │           └── impl/
│   │               └── ClienteServiceImpl.java  # Implementação do serviço
│   └── resources/
│       └── application.properties               # Configurações da aplicação
└── test/
    └── java/
        └── one/digitalinnovation/gof/
            └── LabPadroesProjetoSpringApplicationTests.java
```

## 🚀 Como Executar

### Pré-requisitos

- Java 11 ou superior
- Maven 3.6+

### Executando a aplicação

1. **Clone o repositório:**
```bash
git clone <url-do-repositorio>
cd padroes-projeto-spring
```

2. **Compile o projeto:**
```bash
./mvnw clean install
```

3. **Execute a aplicação:**
```bash
./mvnw spring-boot:run
```

4. **Acesse a aplicação:**
   - API Base: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: *(deixe em branco)*

## 📚 Endpoints da API

### Clientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/clientes` | Lista todos os clientes |
| GET | `/clientes/{id}` | Busca cliente por ID |
| POST | `/clientes` | Cadastra novo cliente |
| PUT | `/clientes/{id}` | Atualiza cliente existente |
| DELETE | `/clientes/{id}` | Remove cliente |

### Exemplo de Requisição POST/PUT

```json
{
  "nome": "João Silva",
  "endereco": {
    "cep": "01310-100"
  }
}
```

*Obs: O sistema busca automaticamente os dados do endereço na API do ViaCEP usando o CEP informado.*

### Exemplo de Resposta

```json
{
  "id": 1,
  "nome": "João Silva",
  "endereco": {
    "cep": "01310-100",
    "logradouro": "Avenida Paulista",
    "complemento": "",
    "bairro": "Bela Vista",
    "localidade": "São Paulo",
    "uf": "SP",
    "ibge": "3550308",
    "gia": "1004",
    "ddd": "11",
    "siafi": "7107"
  }
}
```

## ✅ Funcionalidades Implementadas

- ✅ CRUD completo de clientes
- ✅ Integração automática com API ViaCEP
- ✅ Validação de dados com Bean Validation
- ✅ Tratamento global de exceções
- ✅ Documentação automática com Swagger/OpenAPI
- ✅ Banco de dados H2 em memória
- ✅ Console H2 para visualização de dados
- ✅ Logs configurados para debug
- ✅ Padrões REST adequados (códigos HTTP corretos)

## 🧪 Testando a API

Você pode testar a API usando:

1. **Swagger UI** (Recomendado): `http://localhost:8080/swagger-ui.html`
2. **Postman/Insomnia**: Importe os endpoints
3. **cURL**: 
```bash
# Listar todos os clientes
curl http://localhost:8080/clientes

# Criar novo cliente
curl -X POST http://localhost:8080/clientes \
  -H "Content-Type: application/json" \
  -d '{"nome":"Maria Santos","endereco":{"cep":"01310-100"}}'
```

## 🎓 Conceitos Aplicados

### Design Patterns

- **Singleton**: O Spring gerencia os beans como singletons por padrão
- **Strategy**: Separação de interfaces e implementações (ClienteService/ClienteServiceImpl)
- **Facade**: ClienteRestController simplifica acesso às funcionalidades complexas
- **Repository**: Abstração do acesso a dados com Spring Data JPA
- **Proxy**: OpenFeign cria proxies para chamadas HTTP declarativas

### Boas Práticas

- Separação de responsabilidades (Controllers, Services, Repositories)
- Validação de entrada de dados
- Tratamento centralizado de exceções
- Documentação automática de API
- Uso de DTOs implícitos através de entidades JPA
- Código limpo e bem comentado
- Configurações externalizadas

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.


---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!
