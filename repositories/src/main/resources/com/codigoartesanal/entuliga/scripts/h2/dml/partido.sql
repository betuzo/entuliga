alter sequence partido_id_seq restart with 2;
INSERT INTO PARTIDO (ID, JORNADA_ID, LOCAL_ID, PUNTOS_LOCAL, COLOR_LOCAL, VISITANTE_ID, PUNTOS_VISITANTE, COLOR_VISITANTE, CANCHA_ID, HORARIO, STATUS) VALUES
(1, 1, 1, 2, '#0614f3', 2, 0, '#000000', 1, TIMESTAMP '2015-09-04 14:25:00.000', 'PROGRAMADO');