CREATE TABLE IF NOT EXISTS MUNICIPIO (
    ID SERIAL PRIMARY KEY,
    NOMBRE VARCHAR(50) NOT NULL,
    NOMBRE_OFICIAL VARCHAR(70) NOT NULL,
    ESTADO_ID BIGINT NOT NULL
);

INSERT INTO MUNICIPIO (ID, NOMBRE, NOMBRE_OFICIAL, ESTADO_ID) VALUES
(1, 'Mixquiahuala', 'Mixquiahuala de Juárez', 1);
