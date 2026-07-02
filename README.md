# Serviço de Transferências com Vue.js e Spring Boot

Aplicação para agendamento de transferências bancárias. O back-end expõe uma
API REST em Spring Boot responsável por calcular taxas e agendar
transferências; o front-end, em Vue 3, oferece o formulário de agendamento e a
listagem das transferências já agendadas.

## Estrutura do projeto

```
transferencia-agendamento/                # raiz do projeto Spring Boot 2.7 (API)
├── src/
│   └── main/
│       ├── java/...                      # código-fonte da API
│       └── resources/
│           └── application.properties
├── pom.xml
└── frontend/                             # aplicação Vue 3.5 (SPA)
    ├── src/
    │   ├── components/
    │   │   ├── HomePage.vue              # listagem de transferências agendadas
    │   │   ├── AgendamentoPage.vue       # formulário de agendamento
    │   │   └── TabelaAgendamentos.vue    # tabela das transferências agendadas
    │   ├── router/...
    │   ├── App.vue
    │   └── main.ts
    ├── buildToProject.sh                 # script para copiar o SPA de forma statica para o servidor
    ├── index.html
    ├── package.json
    └── vite.config.ts
```

## Tecnologias

**Back-end**
- Java + Spring Boot
- Spring Web (REST API)
- Maven
- H2 RDBMS, JPA / Hibernate
- Tomcat (Opcional, apenas caso queira servir o SPA staticamente)

**Front-end**
- Vue 3
- Vue Router
- Vite
- [Web Awesome](https://webawesome.com/) Esta biblioteca foi utilizada pois sendo de componentes de html nativo, é compativel com qualquer framework, e é altamente customizável.
- [Vue Sooner](https://vue-sonner.vercel.app/) Utilizado para lançar mensagem tipo "Toast" na tela.

## Pré-requisitos

- Java 11+
- Maven 3.8+ 
- Node.js 18+
- npm

## Como rodar o back-end

A partir da raiz do projeto (`transferencia-agendamento/`):

```bash
# Maven
./mvnw spring-boot:run
```

Por padrão a API sobe em `http://localhost:8080`.

## Como rodar o front-end

A partir da pasta `transferencia-agendamento/frontend/`:

```bash
npm install
npm run dev
```

A aplicação abre em `http://localhost:5173` (porta padrão do Vite) e consome
a API do back-end. 

## Build de produção do front-end

```bash
npm run build
```

Os arquivos estáticos são gerados em `frontend/dist/`. Você pode servi-los
diretamente pelo Spring Boot (copiando o conteúdo para
`src/main/resources/static/`), o script buildToProject.sh já gera os arquivos e copia.

## Endpoints da API

| Método | Rota                        | Descrição                                              |
|--------|-----------------------------|---------------------------------------------------------|
| GET    | `/api/listar-transferencias`| Lista as transferências agendadas                       |
| POST   | `/api/calcula-taxa`         | Calcula a taxa e o valor total de uma transferência      |
| POST   | `/api/agenda-transferencia` | Confirma e agenda a transferência                        |

### Exemplo de payload — `POST /api/calcula-taxa` e `/api/agenda-transferencia`

```json
{
  "contaOrigem": "00000001",
  "contaDestino": "00000002",
  "valorTransferencia": 10000.00,
  "dataTransferencia": "2026-07-24",
  "dataAgendamento": "2026-07-02"
}
```

### Exemplo de resposta — `POST /api/calcula-taxa`

```json
{
  "taxa": 690.00,
  "valorTotal": 10690.00
}
```
