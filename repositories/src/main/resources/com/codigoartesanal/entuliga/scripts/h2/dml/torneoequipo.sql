alter sequence torneoequipo_id_seq restart with 5;
INSERT INTO TORNEOEQUIPO (ID, TORNEO_ID, EQUIPO_ID, STATUS_EQUIPO) VALUES
(1, 1, 1, 'INSCRITO'),
(2, 1, 2, 'INSCRITO'),
(3, 1, 3, 'INSCRITO'),
(4, 1, 4, 'INSCRITO');