CREATE TABLE IF NOT EXISTS PAIS (
    ID SERIAL PRIMARY KEY,
    NOMBRE VARCHAR(50) NOT NULL,
    ABREVIATURA VARCHAR(5) NOT NULL
);

INSERT INTO PAIS (ID, NOMBRE, ABREVIATURA) VALUES (1, 'México', 'MX');
