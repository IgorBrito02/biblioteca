# 📚 API Biblioteca – Sistema de Gestão

API REST desenvolvida para o **gerenciamento de usuários, livros, exemplares e empréstimos** de uma biblioteca acadêmica.
Este projeto foi implementado como parte da disciplina **Programação Back-End II** do curso de **Pós-Graduação em Desenvolvimento Web Full Stack (Unipê)**.

---

## 🧑‍💻 Desenvolvedor

**Igor Pinheiro de Brito**
📧 [igor_pbrito@hotmail.com](mailto:igor_pbrito@hotmail.com)
🔗 [GitHub – IgorBrito02](https://github.com/IgorBrito02/biblioteca)

---

## 🚀 Tecnologias Utilizadas

| Categoria | Tecnologias |
|------------|--------------|
| **Linguagem** | Java 21 |
| **Framework** | Spring Boot 3.5.6 |
| **Módulos do Spring** | Spring Web, Spring Data JPA, Spring Boot DevTools, Validation |
| **Anotações Utilitárias** | Lombok |
| **Banco de Dados – Produção** | MySQL Driver |
| **Banco de Dados – Desenvolvimento/Testes** | H2 Database |
| **Documentação** | Springdoc OpenAPI (Swagger UI) |
| **Logs** | SLF4J + Logback |
| **Gerenciador de Dependências** | Maven |
| **IDE Recomendada** | VS Code / IntelliJ IDEA / STS |

---

## ⚙️ Funcionalidades Implementadas

✅ CRUD completo para Usuário, Autor, Livro, Exemplar e Empréstimo
✅ Busca de empréstimos por intervalo de data (`GET /emprestimos?inicio=...&fim=...`)
✅ Validação de disponibilidade dos exemplares
✅ Tratamento de exceções globais com `@ControllerAdvice`
✅ Sistema de logs com SLF4J + Logback (`biblioteca-erros.log`)
✅ Documentação interativa com Swagger UI (`SwaggerConfig.java`)

---

## 🧪 SEQUÊNCIA DE TESTES – RESULTADOS REAIS (2025-10-06)

Todos os **11 testes práticos** foram executados com sucesso via Swagger UI.
Abaixo estão os resultados **reais retornados pela API** durante a execução.

---

### 🔹 PASSO 1 — Criar Usuário

**Endpoint:** `POST /usuarios`
**JSON enviado:**
```json
{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "cpf": "12345678900"
}
```
✅ **Retornou:**
```json
{
  "id": "d07ababe-9d49-4cba-911a-28dc861580b8",
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "cpf": "12345678900"
}
```

---

### 🔹 PASSO 2 — Criar Autor

**Endpoint:** `POST /autores`
```json
{
  "nome": "J. K. Rowling",
  "nacionalidade": "Britânica"
}
```
✅ **Retornou:**
```json
{
  "id": "63f77fc1-3b05-4c74-84ff-34f8d4f59e50",
  "nome": "J. K. Rowling",
  "nacionalidade": "Britânica"
}
```

---

### 🔹 PASSO 3 — Criar Livro

**Endpoint:** `POST /livros`
```json
{
  "titulo": "Harry Potter e a Pedra Filosofal",
  "descricao": "Harry Potter descobre que é um bruxo e vai para Hogwarts.",
  "qtdePaginas": 223,
  "isbn": "9780747532699",
  "editora": "Rocco",
  "anoPublicacao": 1997,
  "autoresIds": ["63f77fc1-3b05-4c74-84ff-34f8d4f59e50"]
}
```
✅ **Retornou:**
```json
{
  "id": "c99c2da8-e79b-435c-bb53-2fd35d6da2e4",
  "titulo": "Harry Potter e a Pedra Filosofal",
  "descricao": "Harry Potter descobre que é um bruxo e vai para Hogwarts.",
  "qtdePaginas": 223,
  "isbn": "9780747532699",
  "editora": "Rocco",
  "anoPublicacao": 1997,
  "autores": [
    {
      "id": "63f77fc1-3b05-4c74-84ff-34f8d4f59e50",
      "nome": "J. K. Rowling",
      "nacionalidade": "Britânica"
    }
  ]
}
```

---

### 🔹 PASSO 4 — Criar Exemplar do Livro

**Endpoint:** `POST /exemplares`
```json
{
  "codigo": "HP001",
  "status": "DISPONIVEL",
  "statusConservacao": "NOVO",
  "livroId": "c99c2da8-e79b-435c-bb53-2fd35d6da2e4"
}
```
✅ **Retornou:**
```json
{
  "id": "4075895e-18bf-4f78-8eed-ac8f749499c4",
  "codigo": "HP001",
  "status": "DISPONIVEL",
  "statusConservacao": "NOVO",
  "livroId": "c99c2da8-e79b-435c-bb53-2fd35d6da2e4"
}
```

---

### 🔹 PASSO 5 — Realizar Empréstimo

**Endpoint:** `POST /emprestimos`
```json
{
  "usuarioId": "d07ababe-9d49-4cba-911a-28dc861580b8",
  "exemplaresIds": ["4075895e-18bf-4f78-8eed-ac8f749499c4"]
}
```
✅ **Retornou:**
```json
{
  "id": "87780040-c5ce-4d12-a2d3-c37e19cf27a9",
  "dataHoraEmprestimo": "2025-10-06T00:14:24.9788138",
  "usuarioId": "d07ababe-9d49-4cba-911a-28dc861580b8",
  "exemplaresIds": ["4075895e-18bf-4f78-8eed-ac8f749499c4"]
}
```

---

### 🔹 PASSO 6 — Listar Empréstimos

**Endpoint:** `GET /emprestimos`
✅ **Retornou lista de empréstimos:**
```json
[
  {
    "id": "87780040-c5ce-4d12-a2d3-c37e19cf27a9",
    "dataHoraEmprestimo": "2025-10-06T00:14:24.978814",
    "dataHoraDevolucao": null,
    "usuarioId": "d07ababe-9d49-4cba-911a-28dc861580b8",
    "exemplaresIds": ["4075895e-18bf-4f78-8eed-ac8f749499c4"]
  }
]
```

---

### 🔹 PASSO 7 — Buscar Empréstimos por Período

**Endpoint:** `GET /emprestimos?inicio=2025-10-01T00:00:00&fim=2025-10-06T23:59:59`
✅ **Retornou empréstimos no intervalo solicitado.**

---

### 🔹 PASSO 8 — Atualizar Empréstimo (Devolver Exemplar)

**Endpoint:** `PUT /emprestimos/{id}`
```json
{
  "dataHoraDevolucao": "2025-10-06T03:32:00"
}
```
✅ **Retornou:**
```json
{
  "id": "87780040-c5ce-4d12-a2d3-c37e19cf27a9",
  "dataHoraEmprestimo": "2025-10-06T00:14:24.978814",
  "dataHoraDevolucao": "2025-10-06T03:32:00",
  "usuarioId": "d07ababe-9d49-4cba-911a-28dc861580b8",
  "exemplaresIds": ["4075895e-18bf-4f78-8eed-ac8f749499c4"]
}
```

---

### 🔹 PASSO 9 — Verificar Contagem de Exemplares

**Endpoint:** `GET /livros/{id}/exemplares/count`
✅ **Retornou:**
```json
{
  "titulo": "Harry Potter e a Pedra Filosofal",
  "totalExemplares": 1,
  "disponiveis": 1,
  "emprestados": 0,
  "reservados": 0
}
```

---

### 🔹 PASSO 10 — Testar Log de Erro

Tentativa de novo empréstimo com exemplar já emprestado.
✅ **Retornou erro 400 (Bad Request):**
```json
{
  "timestamp": "2025-10-06T00:57:10.6998899",
  "status": 400,
  "error": "Regra de Negócio Violada",
  "message": "Alguns exemplares não estão disponíveis para empréstimo."
}
```
📄 **Erro registrado em:** `logs/biblioteca-erros.log`

---

### 🔹 PASSO 11 — Deleções

Foram realizados `DELETE` para todas as entidades (empréstimos, exemplares, livros, autores e usuários).
✅ **Todos retornaram 204 No Content**, exceto o último (`DELETE /usuarios/{id}`), que gerou erro 500 esperado e registrado no log.

---

## 🧾 Log de Erros (Logback)

Arquivo: `src/main/resources/logback-spring.xml`
Exemplo real de registro:
```
2025-10-06 00:57:10 ERROR [EmprestimoService] - Alguns exemplares não estão disponíveis para empréstimo.
2025-10-06 03:32:00 INFO  [EmprestimoService] - Empréstimo finalizado com sucesso.
```

---

## 🧱 Requisitos Técnicos Atendidos

- [x] CRUD completo das entidades
- [x] Busca por intervalo de data
- [x] Validação de disponibilidade
- [x] Logs configurados com Logback
- [x] Documentação Swagger customizada
- [x] Banco MySQL (produção) e H2 (testes)
- [x] Testes funcionais realizados e validados

---

## 🧩 Execução do Projeto

```bash
# Clone o repositório
git clone https://github.com/IgorBrito02/biblioteca.git

# Acesse o diretório
cd biblioteca

# Execute a aplicação
mvn spring-boot:run
```

Acesse o Swagger UI em:
👉 [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

---

## 🧾 Licença

Este projeto é licenciado sob a **Apache License 2.0**.
© 2025 – Desenvolvido por **Igor Pinheiro de Brito**.