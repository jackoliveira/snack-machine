# Desafio Voll

Tarefa: Projetar esse novo sistema em nuvem que irá substituir o antigo para que as
        máquinas de Snacks já instaladas nas grandes empresas (clientes) voltem a funcionar.

A API deve disponibilizar as seguintes funcionalidades:
- Mostrar o saldo para o usuário;
- Fazer uma venda de Snack, atualizando o saldo do usuário;

## Funcionalidades

  - Exibição e Recarga de saldo.
  - Simulação da Compra de Snacks.

Tecnologias utilizadas
  - Java;
  - Hibernate;
  - PostgreSQL;
  - Spring Boot;
  - Spring Data;
  - Lombok;
  - H2 Database;
  
- É utilizado um banco de dados em memória para fácil utilização deste prototipo.

### Installation

Requisitos
 - Ambiente de Desenvolvimento Java

Porta API [localhost:8080](http://localhost:8080)
Porta do Banco [localhost:8080/h2](http://localhost:8080/h2)
    - JDBC URL = jdbc:h2:mem:snackmachine
    - User Name = sa

Rotas
- GET `/account/{userId}/balance`
- GET `/account/{userId}/balance/recharge`
- POST `/account/{userId}/purchase`