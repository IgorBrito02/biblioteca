# 📚 Biblioteca API

API REST desenvolvida em **Spring Boot** para gerenciar **Autores**, **Livros** e **Exemplares** de uma biblioteca.  
O projeto segue uma arquitetura em camadas (**Controller, Service, Repository, DTOs e Models**) e permite operações de **CRUD** completas, além de funcionalidades extras como **busca por título** e **contagem de exemplares por status**.

---

## ⚙️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Web**
- **Spring Data JPA**
- **Spring Boot DevTools**
- **Validation**
- **Lombok**
- **MySQL Driver** (banco de dados externo – produção)  
- **H2 Database** (banco em memória – desenvolvimento/testes)

---

## 📦 Estrutura do Projeto

src/main/java/com/igor/biblioteca
│
├── controller # Endpoints REST (Autores, Livros, Exemplares)
├── dto # Objetos de Transferência de Dados
├── model # Entidades JPA (Autor, Livro, ExemplarLivro)
├── repository # Interfaces de persistência (Spring Data JPA)
├── service # Interfaces de negócio
└── service/impl # Implementações dos serviços

yaml
Copiar código

---

## 🚀 Como Executar

### 1. Clonar o repositório
```bash
git clone https://github.com/IgorBrito02/biblioteca.git
cd biblioteca
```
### 2. Configurar o banco de dados
No arquivo application.yml, já existem dois perfis configurados:

- **dev** → usa banco H2 em memória (mais simples para rodar localmente).
- **prod** → configurado para MySQL.

O projeto inicia por padrão no perfil dev (porta 8081).  
Para rodar com MySQL, configure suas credenciais no application.yml e ative o perfil prod.

### 3. Executar o projeto
O exercício foi executado com o comando abaixo:

```bash
mvn spring-boot:run
```

A API estará disponível em:  
👉 http://localhost:8081

---

## 📌 Endpoints Disponíveis

### 🔹 Autores
- POST /autores → cria um autor
- GET /autores → lista todos
- GET /autores/{id} → busca por ID
- PUT /autores/{id} → atualiza
- DELETE /autores/{id} → deleta

### 🔹 Livros
- POST /livros → cria um livro
- GET /livros → lista todos
- GET /livros/{id} → busca por ID
- PUT /livros/{id} → atualiza
- DELETE /livros/{id} → deleta
- GET /livros/search?titulo={titulo} → busca por título
- GET /livros/{id}/exemplares/count → contagem geral de exemplares
- GET /livros/{id}/exemplares/count?status=DISPONIVEL → contagem filtrada por status

### 🔹 Exemplares
- POST /exemplares → cria um exemplar
- GET /exemplares → lista todos
- GET /exemplares/{id} → busca por ID
- PUT /exemplares/{id} → atualiza
- DELETE /exemplares/{id} → deleta

---

## 🧪 Roteiro de Testes (Postman)

Durante os testes, foram criadas 17 requisições no Postman, cobrindo o ciclo completo da API:

1. Criar Autor → POST http://localhost:8081/autores
2. Listar Autores → GET http://localhost:8081/autores
3. Criar Livro → POST http://localhost:8081/livros
4. Listar Livros → GET http://localhost:8081/livros
5. Criar Exemplares → POST http://localhost:8081/exemplares
6. Listar Exemplares → GET http://localhost:8081/exemplares
7. Buscar Livro por Título → GET http://localhost:8081/livros/search?titulo=Harry
8. Contar Exemplares do Livro (todos) → GET http://localhost:8081/livros/{livroId}/exemplares/count
9. Contar Exemplares Disponíveis → GET http://localhost:8081/livros/{livroId}/exemplares/count?status=DISPONIVEL
10. Contar Exemplares Emprestados → GET http://localhost:8081/livros/{livroId}/exemplares/count?status=EMPRESTADO
11. Contar Exemplares Reservados → GET http://localhost:8081/livros/{livroId}/exemplares/count?status=RESERVADO
12. Atualizar Autor → PUT http://localhost:8081/autores/{autorId}
13. Atualizar Livro → PUT http://localhost:8081/livros/{livroId}
14. Atualizar Exemplar → PUT http://localhost:8081/exemplares/{exemplarId}
15. Deletar Exemplar → DELETE http://localhost:8081/exemplares/{exemplarId}
16. Deletar Livro → DELETE http://localhost:8081/livros/{livroId}
17. Deletar Autor → DELETE http://localhost:8081/autores/{autorId}

---

## ✅ Exemplo de JSON (Criar Livro)

```json
{
  "titulo": "Harry Potter e a Pedra Filosofal",
  "descricao": "Harry Potter é um garoto cujos pais, feiticeiros, foram assassinados por um poderosíssimo bruxo quando ele ainda era um bebê...",
  "isbn": "9780747532699",
  "editora": "Rocco",
  "anoPublicacao": 1997,
  "qtdePaginas": 223,
  "autoresIds": ["87bba4e9-f43d-4476-923f-2a90ded5cbfb"]
}
```

---

## 📖 Conclusão
Este projeto implementa uma API REST completa de Biblioteca, com CRUD de Autores, Livros e Exemplares, além de funcionalidades adicionais como busca por título e contagem de exemplares por status.

A aplicação foi validada com testes no Postman, cobrindo todo o fluxo da API e confirmando que todas as operações funcionam corretamente.