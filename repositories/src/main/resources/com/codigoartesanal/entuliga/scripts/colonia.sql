CREATE TABLE IF NOT EXISTS COLONIA (
    ID SERIAL PRIMARY KEY,
    NOMBRE VARCHAR(50) NOT NULL,
    MUNICIPIO_ID BIGINT NOT NULL
);

INSERT INTO COLONIA (ID, NOMBRE, MUNICIPIO_ID) VALUES
(1, 'El Bondho', 1);
