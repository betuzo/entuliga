CREATE TABLE IF NOT EXISTS MUNICIPIO (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(50) NOT NULL,
    NOMBRE_OFICIAL VARCHAR(70) NOT NULL,
    ESTADO_ID BIGINT NOT NULL
);