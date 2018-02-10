alter sequence torneo_id_seq restart with 2;
INSERT INTO TORNEO (ID, LIGA_ID, NOMBRE, CLAVE, DESCRIPCION, FECHA_INICIO, FECHA_FIN, STATUS) VALUES
(1, 1, 'Primera Fuerza Verano 2015', 'primera-fuerza-verano-2015', 'Enero a Junio 2015 Primer Fuerza', TIMESTAMP '2015-01-01 14:25:00.000', TIMESTAMP '2015-01-15 14:25:00.000', 'ENPROCESO');