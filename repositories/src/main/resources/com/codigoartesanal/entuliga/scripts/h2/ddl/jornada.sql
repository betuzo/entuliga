CREATE TABLE IF NOT EXISTS JORNADA (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    TORNEO_ID BIGINT NOT NULL,
    NOMBRE VARCHAR(50) NOT NULL,
    FASE VARCHAR(50) NOT NULL
);