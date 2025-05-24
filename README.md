# Legenda do Diagrama de Arquitetura

## Componentes

- **App Expo (React Native)**: Aplicativo móvel utilizado pelo usuário para login, agendamento de coletas e conclusão de entregas.
- **API Spring Boot**: Camada backend responsável pelas regras de negócio, manipulação de dados e autenticação.
- **Banco H2 (modo arquivo)**: Banco de dados leve e embutido, utilizado para armazenar as entidades do sistema.

## Principais Endpoints

- `POST /auth/login`: Realiza autenticação do usuário.
- `GET /usuarios`: Lista todos os usuários.
- `POST /usuarios`: Cria um novo usuário.
- `PUT /usuarios/{id}`: Atualiza um usuário existente.
- `DELETE /usuarios/{id}`: Remove um usuário.
- `GET /veiculos`: Lista todos os veículos.
- `POST /veiculos`: Cria um novo veículo.
- `PUT /veiculos/{id}`: Atualiza um veículo existente.
- `DELETE /veiculos/{id}`: Remove um veículo.
- `POST /coletas`: Cria uma nova ordem de coleta.
- `PATCH /coletas/:id/concluir`: Finaliza uma coleta já existente.

## Entidades

- **Usuário**
    - `id`, `email`, `senhaHash`, `papel`
- **Veículo**
    - `id`, `placa`, `modelo`, `cor`
- **Coleta**
    - `id`, `origem`, `destino`, `data`, `status`, `usuarioId`

## Organização da API

- **controller**: Pontos de entrada da API.
- **service**: Regras de negócio.
- **repository**: Persistência dos dados.
- **model**: Representação das entidades.
- **dto**: Transferência de dados entre camadas.
- **config**: Configurações da aplicação.
- **exception**: Tratamento centralizado de erros e exceções.
## Fluxo Completo

1. O usuário faz login com e-mail e senha.
2. Com o token JWT, agenda uma coleta via `POST /coletas`.
3. Pode cadastrar, listar, editar e remover usuários e veículos.
4. Após a coleta, o motorista conclui a entrega via `PATCH /coletas/:id/concluir`.