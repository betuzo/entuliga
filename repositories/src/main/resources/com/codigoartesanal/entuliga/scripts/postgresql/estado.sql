CREATE TABLE IF NOT EXISTS ESTADO (
    ID SERIAL PRIMARY KEY,
    NOMBRE VARCHAR(50) NOT NULL,
    ABREVIATURA VARCHAR(5) NOT NULL,
    PAIS_ID BIGINT NOT NULL
);

INSERT INTO ESTADO (ID, NOMBRE, ABREVIATURA, PAIS_ID) VALUES (1, 'Hidalgo', 'HGO', 1);
