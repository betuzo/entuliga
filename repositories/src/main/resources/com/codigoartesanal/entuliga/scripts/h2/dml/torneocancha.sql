alter sequence torneocancha_id_seq restart with 3;
INSERT INTO TORNEOCANCHA (ID, TORNEO_ID, CANCHA_ID, STATUS_CANCHA) VALUES
(1, 1, 1, 'INSCRITO'),
(2, 1, 2, 'INSCRITO');