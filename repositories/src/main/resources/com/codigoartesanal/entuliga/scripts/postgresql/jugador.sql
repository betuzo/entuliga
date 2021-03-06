CREATE TABLE IF NOT EXISTS JUGADOR (
    ID SERIAL PRIMARY KEY,
    ADMIN_ID VARCHAR(25) NOT NULL,
    NOMBRE VARCHAR(50) NOT NULL,
    PATERNO VARCHAR(70) NOT NULL,
    MATERNO VARCHAR(70) NOT NULL,
    RUTA_FOTO VARCHAR(70) NOT NULL,
    SEXO VARCHAR(70) NOT NULL,
    FECHA_REGISTRO TIMESTAMP NOT NULL,
    GEOLOCATION_ID BIGINT NOT NULL
);

INSERT INTO JUGADOR (ID, ADMIN_ID, NOMBRE, PATERNO, MATERNO, RUTA_FOTO, SEXO, FECHA_REGISTRO, GEOLOCATION_ID) VALUES
(1, 'rolguin@grupobmv.com.mx', 'Roberto Salvador', 'Olguin', 'Lozano', 'asdasd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 1),
(2, 'sgarcia', 'Salvador Isaias', 'Garcia', 'Olguin', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(3, 'jmolina', 'Javier Ignacio', 'Molina', 'Cano', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(4, 'snaranjo', 'Sixto', 'Naranjo', 'Marín', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(5, 'gduque', 'Gerardo Emilio', 'Duque', 'Gutiérrez', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(6, 'jsaenz', 'Jhony Alberto', 'Sáenz', 'Hurtado', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(7, 'gloreto', 'Germán Antonio', 'Lotero', 'Upegui', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(8, 'omurillo', 'Oscar Darío', 'Murillo', 'González', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(9, 'aosorno', 'Augusto', 'Osorno', 'Gil', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(10, 'cpalacio', 'César Oswaldo', 'Palacio', 'Martínez', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(11, 'hgonzalez', 'Héctor Iván', 'González', 'Castaño', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(12, 'cmontoya', 'Carlos Mario', 'Montoya', 'Serna', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(13, 'atabares', 'Arturo', 'Tabares', 'Mora', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2),
(14, 'jlopez', 'Jaime', 'Lopez', 'Tobón', 'asdassd', 'MASCULINO', TIMESTAMP '2015-01-01 14:25:00.000', 2);
