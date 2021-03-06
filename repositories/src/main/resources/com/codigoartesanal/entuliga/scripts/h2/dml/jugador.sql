alter sequence jugador_id_seq restart with 16;

INSERT INTO JUGADOR (ID, ADMIN_ID, NOMBRE, PATERNO, MATERNO, RUTA_FOTO, SEXO, FECHA_REGISTRO, GEOLOCATION_ID) VALUES
(1, 'rolguin@grupobmv.com.mx', 'Roberto Salvador', 'Olguin', 'Lozano', '1.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 1),
(2, 'sgarcia', 'Salvador Isaias', 'Garcia', 'Olguin', '2.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(3, 'jmolina', 'Javier Ignacio', 'Molina', 'Cano', '3.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(4, 'snaranjo', 'Sixto', 'Naranjo', 'Marin', '4.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(5, 'gduque', 'Gerardo Emilio', 'Duque', 'Gutierrez', '5.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(6, 'jsaenz', 'Jhony Alberto', 'Saenz', 'Hurtado', '6.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(7, 'gloreto', 'German Antonio', 'Lotero', 'Upegui', '7.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(8, 'omurillo', 'Oscar Dario', 'Murillo', 'Gonzalez', '8.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(9, 'aosorno', 'Augusto', 'Osorno', 'Gil', '9.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(10, 'cpalacio', 'Cesar Oswaldo', 'Palacio', 'Martinez', '10.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(11, 'hgonzalez', 'Hector Ivan', 'Gonzalez', 'Castillo', '11.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(12, 'cmontoya', 'Carlos Mario', 'Montoya', 'Serna', '12.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(13, 'atabares', 'Arturo', 'Tabares', 'Mora', '13.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(14, 'jlopez', 'Jaime', 'Lopez', 'Tobon', '14.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(15, 'jperez@tu.me', 'Juan', 'Perez', 'Cruz', '15.png', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2);
