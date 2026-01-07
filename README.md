# 📊 Relatórios de Vendas com Spring Boot & JPQL
### 📖 Descrição

Este projeto tem como objetivo a prática e consolidação de consultas JPQL em uma aplicação Spring Boot com Spring Data JPA, simulando cenários reais de geração de relatórios e sumarizações de dados.
Foram implementados casos de uso que envolvem filtros dinâmicos, paginação, funções de agregação, agrupamentos e otimização de consultas, com foco em performance e boas práticas no acesso ao banco de dados.

### 🎯 Objetivos do Projeto

- Desenvolver relatórios utilizando JPQL
- Aplicar filtros opcionais de data e texto
- Utilizar paginação nos resultados
- Implementar funções de agregação como COUNT e SUM
- Utilizar GROUP BY para sumarização de dados
- Aplicar @EntityGraph para otimizar consultas e evitar o problema N+1
- Separar responsabilidades entre Controller, Service e Repository
---
## 🧩 Casos de Uso
### 📌 Relatório de Vendas

- Entrada (opcional):
- Data inicial
- Data final
- Trecho do nome do vendedor
- Saída:
- Listagem paginada contendo:
- ID da venda
- Data
- Quantidade vendida
- Nome do vendedor

### Regras:

- Se a data final não for informada, considera a data atual do sistema
- Se a data inicial não for informada, considera 1 ano antes da data final
- Se o nome do vendedor não for informado, considera texto vazio
- Os parâmetros são recebidos como String no controller e tratados no service

### 📌 Sumário de Vendas por Vendedor

- Entrada (opcional):
- Data inicial
- Data final
- Saída:
- Listagem contendo:
- Nome do vendedor
- Soma total de vendas no período informado

### Regras:

- As mesmas regras de datas aplicadas no relatório de vendas
---
## 🧠 Conceitos Aplicados
### 🔹 JPQL

- Consultas orientadas a entidades
- Uso de JOIN entre entidades
- Filtros dinâmicos com parâmetros opcionais

### 🔹 Funções de Agregação

- COUNT para contagem de registros
- SUM para totalização de valores

### 🔹 GROUP BY

- Agrupamento de dados por vendedor
- Geração de relatórios e sumarizações

### 🔹 Paginação

- Uso de Pageable e Page
- Retorno paginado diretamente no endpoint

### 🔹 @EntityGraph

- Carregamento otimizado de relacionamentos
- Prevenção do problema N+1 Select
- Redução do número de queries executadas no banco
---
## 🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- JPQL
- Maven
- Banco de Dados Relacional (PostgreSQL / H2)
---
## 🚀 Aprendizados

- Escrita de consultas JPQL eficientes
- Uso correto de GROUP BY e funções de agregação
- Importância do tratamento de dados no service
- Ganhos reais de performance com @EntityGraph
- Implementação de relatórios paginados no backend

## 📌 Observações

- Este projeto possui caráter educacional, sendo ideal para:
- Estudo de Spring Data JPA
- Treinamento de consultas avançadas
- Compreensão de performance em aplicações backend
---
## 📂 Estrutura do Projeto
```text
src/main/java
 ├── controllers     # Endpoints REST
 ├── services        # Regras de negócio e tratamento de datas
 ├── repositories    # Consultas JPQL
 ├── dto             # Projeções e respostas otimizadas
 └── entities        # Entidades JPA
