INSERT INTO missoes (corpo_celeste, status, data_lancamento, descricao, nome)
VALUES ('MARTE', 'EM_ORBITA', '2026-01-15 08:00:00',
        'Primeira missão S3A para Marte — região Tharsis Bulge', 'ARES-1');

INSERT INTO missoes (corpo_celeste, status, data_lancamento, descricao, nome)
VALUES ('LUA', 'PLANEJADA', '2027-03-20 12:00:00',
        'Missão lunar — mapeamento de tubos de lava na região Mare Tranquillitatis', 'LUNA-1');

INSERT INTO mini_droids (codigo, missao_id, status, latitude, longitude,
                         profundidade_metros, data_fincagem, bateria_percentual)
VALUES ('S3A-M-001', 1, 'ATIVO', 14.5, -120.3, 0.45, '2026-03-10 14:22:00', 87.5);

INSERT INTO mini_droids (codigo, missao_id, status, latitude, longitude,
                         profundidade_metros, data_fincagem, bateria_percentual)
VALUES ('S3A-M-002', 1, 'ATIVO', 14.7, -120.1, 0.52, '2026-03-10 14:25:00', 91.0);

INSERT INTO mini_droids (codigo, missao_id, status, latitude, longitude,
                         profundidade_metros, data_fincagem, bateria_percentual)
VALUES ('S3A-M-003', 1, 'FALHA', 14.9, -119.8, 0.0, '2026-03-10 14:28:00', 12.3);

INSERT INTO sensores (mini_droid_id, tipo, ativo, ultima_leitura, data_ultima_leitura)
VALUES (1, 'SISMOMETRO', true, 0.0023, '2026-03-12 09:10:00');

INSERT INTO sensores (mini_droid_id, tipo, ativo, ultima_leitura, data_ultima_leitura, modo_optico)
VALUES (1, 'OPTICO_REFLEXAO', true, 0.77, '2026-03-12 09:10:00', 'OPTICO_REFLEXAO');

INSERT INTO sensores (mini_droid_id, tipo, ativo, ultima_leitura, data_ultima_leitura)
VALUES (2, 'SISMOMETRO', true, 0.0018, '2026-03-12 09:11:00');

INSERT INTO sensores (mini_droid_id, tipo, ativo, ultima_leitura, data_ultima_leitura, modo_optico)
VALUES (2, 'OPTICO_REFLEXAO', true, 0.65, '2026-03-12 09:11:00', 'OPTICO_REFRACAO');

INSERT INTO alertas (mini_droid_id, tipo, mensagem, nivel, resolvido, data_alerta)
VALUES (1, 'CAVIDADE_DETECTADA',
        'Possível cavidade a 320m detectada via reflexão óptica', 'ALTO', false, '2026-03-11 17:45:00');

INSERT INTO alertas (mini_droid_id, tipo, mensagem, nivel, resolvido, data_alerta)
VALUES (3, 'BATERIA_CRITICA',
        'Bateria crítica (12%) — perda de comunicação iminente', 'CRITICO', false, '2026-03-10 22:00:00');