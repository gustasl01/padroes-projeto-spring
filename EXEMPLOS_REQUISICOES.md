# 📋 Exemplos de Requisições - API de Clientes

Este arquivo contém exemplos de requisições HTTP para testar a API.

## 🔧 URLs Importantes

- **API Base**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console

---

## 📝 Endpoints

### 1. Listar Todos os Clientes

```http
GET http://localhost:8080/clientes
```

### 2. Buscar Cliente por ID

```http
GET http://localhost:8080/clientes/1
```

### 3. Criar Novo Cliente

```http
POST http://localhost:8080/clientes
Content-Type: application/json

{
  "nome": "João Silva",
  "endereco": {
    "cep": "01310-100"
  }
}
```

**Outros CEPs para testar:**
- `20040-020` - Rio de Janeiro, RJ
- `30130-010` - Belo Horizonte, MG
- `40020-000` - Salvador, BA
- `50010-000` - Recife, PE
- `60060-000` - Fortaleza, CE
- `70040-020` - Brasília, DF

### 4. Atualizar Cliente

```http
PUT http://localhost:8080/clientes/1
Content-Type: application/json

{
  "nome": "João Silva Atualizado",
  "endereco": {
    "cep": "20040-020"
  }
}
```

### 5. Deletar Cliente

```http
DELETE http://localhost:8080/clientes/1
```

---

## 🧪 Testando com cURL

### Listar todos os clientes
```bash
curl http://localhost:8080/clientes
```

### Buscar cliente por ID
```bash
curl http://localhost:8080/clientes/1
```

### Criar novo cliente
```bash
curl -X POST http://localhost:8080/clientes \
  -H "Content-Type: application/json" \
  -d '{"nome":"Maria Santos","endereco":{"cep":"01310-100"}}'
```

### Atualizar cliente
```bash
curl -X PUT http://localhost:8080/clientes/1 \
  -H "Content-Type: application/json" \
  -d '{"nome":"Maria Santos Silva","endereco":{"cep":"20040-020"}}'
```

### Deletar cliente
```bash
curl -X DELETE http://localhost:8080/clientes/1
```

---

## 🧪 Testando com PowerShell

### Listar todos os clientes
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/clientes" -Method Get
```

### Buscar cliente por ID
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/clientes/1" -Method Get
```

### Criar novo cliente
```powershell
$body = @{
    nome = "Pedro Oliveira"
    endereco = @{
        cep = "01310-100"
    }
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/clientes" -Method Post -Body $body -ContentType "application/json"
```

### Atualizar cliente
```powershell
$body = @{
    nome = "Pedro Oliveira Silva"
    endereco = @{
        cep = "20040-020"
    }
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/clientes/1" -Method Put -Body $body -ContentType "application/json"
```

### Deletar cliente
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/clientes/1" -Method Delete
```

---

## ❌ Exemplos de Erros

### Cliente sem nome (Erro 400)
```json
{
  "nome": "",
  "endereco": {
    "cep": "01310-100"
  }
}
```

### CEP inválido (Erro 400)
```json
{
  "nome": "Teste",
  "endereco": {
    "cep": "00000-000"
  }
}
```

### Cliente não encontrado (Erro 404)
```http
GET http://localhost:8080/clientes/999999
```

---

## 📊 Respostas Esperadas

### Sucesso (200/201)
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

### Erro de Validação (400)
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "errors": {
    "nome": "Nome é obrigatório"
  },
  "message": "Erro de validação"
}
```

### Não Encontrado (404)
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Recurso não encontrado"
}
```
