# S³A — Sondas Sísmicas Autônomas

## Grupo
Camila Mie Takara RM555418
Guilherme Barbiero RM555185
Marco Antônio Gonçalves RM556818
Vinicius Castro RM556137
Matheus Cantiere RM558479

## Motivação

A exploração planetária do século XXI enfrenta um problema concreto: os rovers atuais
— como Curiosity e Perseverance — dependem de mobilidade contínua sobre terrenos
imprevisíveis, estão sujeitos a atolamento, perda de energia e falhas mecânicas que
podem encerrar missões inteiras de alto custo.

Ao mesmo tempo, uma das fronteiras científicas mais relevantes é a busca por cavernas,
tubos de lava e reservatórios subterrâneos de água em Marte e na Lua — estruturas que
podem abrigar vida, proteger bases humanas da radiação cósmica e fornecer recursos para
missões tripuladas.

O projeto **S³A (Sondas Sísmicas Autônomas)** propõe uma alternativa: em vez de um único
rover frágil, uma rede de **mini-droids fincáveis** é dispersa sobre a região-alvo. Cada
droid pousa, crava uma haste no solo (regolito) e se torna uma estação sísmica e óptica
estacionária, autônoma e redundante.

Esta API é o sistema de controle dessa rede — ela gerencia missões, registra os droids,
coleta telemetria dos sensores e monitora alertas gerados automaticamente quando algo
crítico é detectado.

O projeto se alinha ao **ODS 9 da ONU** ao propor infraestrutura científica modular e
resiliente, gerar demanda por pesquisa de fronteira no Brasil e produzir tecnologia com
aplicação terrestre direta: monitoramento de barragens, detecção de deslizamentos e
mapeamento de aquíferos em regiões semiáridas.

## Como o sistema se integra

Em uma missão espacial, os droids são lançados e fincados no solo do corpo celeste determinado. 
O Sensor Sísmico detecta marsquakes e o Sensor Óptico detecta cavidades. 
Os dados são enviados via telemetria para a API S3A e assim, gerando alertas automáticos.

## Endpoints

### Missões
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/missoes` | Lista todas as missões |
| GET | `/api/missoes/{id}` | Busca missão por ID |
| POST | `/api/missoes` | Cria nova missão |
| PUT | `/api/missoes/{id}` | Atualiza missão |
| DELETE | `/api/missoes/{id}` | Remove missão |

### Mini-Droids
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/droids` | Lista todos os droids |
| GET | `/api/droids/{id}` | Busca droid por ID |
| GET | `/api/droids/missao/{id}` | Lista droids de uma missão |
| GET | `/api/droids/bateria-critica` | Droids com bateria abaixo de 15% |
| POST | `/api/droids` | Registra novo droid |
| PUT | `/api/droids/{id}` | Atualiza droid |
| DELETE | `/api/droids/{id}` | Remove droid |
| POST | `/api/droids/{id}/leituras` | Coleta telemetria dos sensores |
| GET | `/api/droids/{id}/relatorio` | Relatório textual do droid |

### Alertas
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/alertas` | Lista todos os alertas |
| GET | `/api/alertas/nao-resolvidos` | Alertas pendentes |
| GET | `/api/alertas/missao/{id}` | Alertas de uma missão |
| GET | `/api/alertas/droid/{id}` | Alertas de um droid |
| POST | `/api/alertas` | Cria alerta manual |
| PATCH | `/api/alertas/{id}/resolver` | Resolve alerta |
| POST | `/api/alertas/verificar-saude/missao/{id}` | Varredura automática de saúde |****
