alter sequence jornada_id_seq restart with 3;
INSERT INTO JORNADA (ID, TORNEO_ID, NOMBRE, FASE) VALUES
(1, 1, 'Jornada 1', 'REGULAR'),
(2, 1, 'Jornada 2', 'REGULAR');