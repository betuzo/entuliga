alter sequence torneojugador_id_seq restart with 15;
INSERT INTO TORNEOJUGADOR (ID, TORNEO_EQUIPO_ID, JUGADOR_ID, STATUS_JUGADOR, POSICION_JUGADOR, NUMERO) VALUES
(1, 1, 1, 'INSCRITO', 'BASE', '1'),
(2, 1, 2, 'INSCRITO', 'ALERO', '3'),
(3, 1, 3, 'INSCRITO', 'ESCOLTA', '2'),
(4, 1, 4, 'INSCRITO', 'ALAPIVOTE', '4'),
(5, 1, 5, 'INSCRITO', 'PIVOTE', '5'),
(6, 1, 6, 'INSCRITO', 'BASE', '6'),
(7, 1, 7, 'INSCRITO', 'ALERO', '7'),
(8, 2, 8, 'INSCRITO', 'BASE', '1'),
(9, 2, 9, 'INSCRITO', 'ALERO', '3'),
(10, 2, 10, 'INSCRITO', 'ESCOLTA', '2'),
(11, 2, 11, 'INSCRITO', 'ALAPIVOTE', '4'),
(12, 2, 12, 'INSCRITO', 'PIVOTE', '5'),
(13, 2, 13, 'INSCRITO', 'BASE', '6'),
(14, 2, 14, 'INSCRITO', 'ALERO', '7');