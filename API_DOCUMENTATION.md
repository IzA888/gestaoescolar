# Gestão Escolar - Documentação de APIs

## Visão Geral

O projeto **Gestão Escolar** é uma aplicação Spring Boot modular para gerenciar instituições educacionais. Utiliza uma arquitetura de módulos independentes com camadas bem definidas (Entity, Repository, Service, Controller).

## Estrutura do Projeto

```
gestaoescolar/
├── src/
│   ├── shared/                 # Módulo compartilhado
│   │   ├── entity/            # Entidades base (BaseEntity)
│   │   ├── dto/               # DTOs base (BaseDTO)
│   │   └── service/           # Interfaces de serviço base
│   │
│   ├── main/
│   │   ├── modulos/
│   │   │   ├── academico/     # Módulo Acadêmico
│   │   │   ├── financeiro/    # Módulo Financeiro
│   │   │   │   ├── api/       # APIs de Financeiro
│   │   │   │   └── interno/   # Serviços internos
│   │   │   └── secretaria/    # Módulo Secretaria
│   │   │
│   │   └── java/
│   │       └── GestaoescolarApplication.java
│   │
│   └── test/                  # Testes unitários
```

## Módulos

### Resumo dos módulos
- `shared`: fornece entidades base, DTOs, serviços comuns e eventos para integração entre módulos.
- `academico`: gerencia a parte acadêmica da escola com alunos, cursos e matrículas.
- `financeiro`: controla contas e transações financeiras da instituição.
- `secretaria`: administra funcionários e agendas de trabalho.

### 1. Shared (Compartilhado)
Contém componentes reutilizáveis por todos os módulos.

**Classes:**
- `EntidadeBaseJPA`: Classe base JPA para todas as entidades persistentes (`id`, `createdAt`, `updatedAt`).
- `BaseDTO`: DTO base com campos comuns.
- `BaseService<T>`: Interface genérica para serviços.
- Eventos e listeners para comunicação entre módulos.

### 2. Acadêmico
Gerencia estudantes, cursos e matrículas.

**Entidades:**
- `Aluno`: estudante com status (ACTIVE, INACTIVE, SUSPENDED, GRADUATED)
- `Curso`: curso com créditos e código único
- `Matricula`: matrícula de aluno em curso

**Resumo de Endpoints:**
- CRUD de alunos com busca por matrícula.
- CRUD de cursos.

**Endpoints:**

#### Students
```
POST   /api/v1/students              - Criar aluno
GET    /api/v1/students              - Listar todos
GET    /api/v1/students/{id}         - Obter por ID
PUT    /api/v1/students/{id}         - Atualizar aluno
DELETE /api/v1/students/{id}         - Deletar aluno
GET    /api/v1/students/registration/{registrationNumber} - Buscar por matrícula
```

#### Courses
```
POST   /api/v1/courses               - Criar curso
GET    /api/v1/courses               - Listar todos
GET    /api/v1/courses/{id}          - Obter por ID
PUT    /api/v1/courses/{id}          - Atualizar curso
DELETE /api/v1/courses/{id}          - Deletar curso
```

### 3. Financeiro
Gerencia contas e transações financeiras.

**Entidades:**
- `Account`: Conta bancária com tipo (CHECKING, SAVINGS, INVESTMENT) e saldo
- `Transaction`: Transação financeira (DEPOSIT, WITHDRAWAL, TRANSFER, PAYMENT, INTEREST)

**Resumo de Endpoints:**
- CRUD de contas com busca por número.
- CRUD de transações com filtro por conta e intervalo de datas.

**Endpoints:**

#### Accounts
```
POST   /api/v1/accounts              - Criar conta
GET    /api/v1/accounts              - Listar todas
GET    /api/v1/accounts/{id}         - Obter por ID
PUT    /api/v1/accounts/{id}         - Atualizar conta
DELETE /api/v1/accounts/{id}         - Deletar conta
GET    /api/v1/accounts/account-number/{accountNumber} - Buscar por número
```

#### Transactions
```
POST   /api/v1/transactions          - Criar transação
GET    /api/v1/transactions          - Listar todas
GET    /api/v1/transactions/{id}     - Obter por ID
GET    /api/v1/transactions/account/{accountId} - Transações por conta
GET    /api/v1/transactions/date-range?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD
DELETE /api/v1/transactions/{id}     - Deletar transação
```

### 4. Secretaria
Gerencia funcionários e agendamentos.

**Entidades:**
- `Employee`: Funcionário com posição (SECRETARY, MANAGER, DIRECTOR, etc)
- `Schedule`: Agenda de funcionário

**Resumo de Endpoints:**
- CRUD de funcionários com busca por número e departamento.
- CRUD de agendas com consulta por funcionário.

**Endpoints:**

#### Employees
```
POST   /api/v1/employees             - Criar funcionário
GET    /api/v1/employees             - Listar todos
GET    /api/v1/employees/{id}        - Obter por ID
PUT    /api/v1/employees/{id}        - Atualizar funcionário
DELETE /api/v1/employees/{id}        - Deletar funcionário
GET    /api/v1/employees/employee-number/{employeeNumber} - Buscar por número
GET    /api/v1/employees/department/{department} - Listar por departamento
```

#### Schedules
```
POST   /api/v1/schedules             - Criar agenda
GET    /api/v1/schedules             - Listar todas
GET    /api/v1/schedules/{id}        - Obter por ID
GET    /api/v1/schedules/employee/{employeeId} - Agendas de funcionário
PUT    /api/v1/schedules/{id}        - Atualizar agenda
DELETE /api/v1/schedules/{id}        - Deletar agenda
```

## Exemplos de Requisições

### Criar Aluno
```json
POST /api/v1/students
Content-Type: application/json

{
  "name": "João Silva",
  "registrationNumber": "STU001",
  "dateOfBirth": "2000-01-15",
  "email": "joao@example.com",
  "phone": "11987654321",
  "address": "Rua Principal, 123",
  "status": "ACTIVE"
}
```

### Criar Conta
```json
POST /api/v1/accounts
Content-Type: application/json

{
  "accountHolder": "Escola XYZ",
  "accountNumber": "ACC001",
  "balance": "50000.00",
  "creditLimit": "100000.00",
  "accountType": "CHECKING",
  "status": "ACTIVE"
}
```

### Criar Transação
```json
POST /api/v1/transactions
Content-Type: application/json

{
  "accountId": 1,
  "transactionDate": "2024-01-15",
  "amount": "1000.00",
  "type": "DEPOSIT",
  "description": "Depósito inicial",
  "status": "COMPLETED"
}
```

### Criar Funcionário
```json
POST /api/v1/employees
Content-Type: application/json

{
  "name": "Maria Santos",
  "employeeNumber": "EMP001",
  "dateOfBirth": "1990-05-20",
  "email": "maria@example.com",
  "phone": "11998765432",
  "department": "Secretaria",
  "position": "SECRETARY",
  "status": "ACTIVE"
}
```

## Padrão de Resposta

### Sucesso (200/201)
```json
{
  "id": 1,
  "name": "João Silva",
  "registrationNumber": "STU001",
  "dateOfBirth": "2000-01-15",
  "email": "joao@example.com",
  "status": "ACTIVE",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### Erro (404/400/500)
```json
{
  "error": "Not Found",
  "message": "Resource not found",
  "status": 404
}
```

## Tecnologias

- **Java 21**
- **Spring Boot 4.0.8-SNAPSHOT**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database**
- **Lombok**
- **Maven**

## Como Executar

1. **Compilar:**
```bash
mvn clean install
```

2. **Executar:**
```bash
mvn spring-boot:run
```

3. **Testar:**
```bash
mvn test
```

## Estrutura de Camadas

Cada módulo segue a arquitetura em camadas:

```
Controller (REST API)
    ↓
Service (Lógica de Negócio)
    ↓
Repository (Acesso a Dados - JPA)
    ↓
Entity (Modelo de Dados)
```

## Convenções

- **DTOs**: Utilizados para transferência de dados entre camadas
- **Enums**: Para valores fixos (Status, Tipo, etc)
- **Lombok**: Para reduzir boilerplate (@Data, @RequiredArgsConstructor)
- **JPA Repositories**: Herdam de JpaRepository para CRUD básico
- **Services**: Injetados via @RequiredArgsConstructor do Lombok

## Testes

Cada módulo possui testes unitários usando:
- **JUnit 5**
- **Mockito** para mocks
- Padrão AAA (Arrange, Act, Assert)

Exemplo em: `src/test/java/com/example/gestaoescolar/`

---

**Versão:** 0.0.1-SNAPSHOT  
**Última Atualização:** 2024
