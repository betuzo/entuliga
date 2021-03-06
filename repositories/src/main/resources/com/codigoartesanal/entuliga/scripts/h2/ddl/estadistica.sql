CREATE TABLE IF NOT EXISTS ENCESTE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PARTIDO_ID BIGINT NOT NULL,
    MINUTO BIGINT NOT NULL,
    SEGUNDO BIGINT NOT NULL,
    TIPO VARCHAR(70) NOT NULL,
    ORIGEN VARCHAR(70) NOT NULL,
    TIRADOR_ID BIGINT NOT NULL,
    VALOR BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS REBOTE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PARTIDO_ID BIGINT NOT NULL,
    MINUTO BIGINT NOT NULL,
    SEGUNDO BIGINT NOT NULL,
    TIPO VARCHAR(70) NOT NULL,
    ORIGEN VARCHAR(70) NOT NULL,
    JUGADOR_ID BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS ASISTENCIA (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PARTIDO_ID BIGINT NOT NULL,
    MINUTO BIGINT NOT NULL,
    SEGUNDO BIGINT NOT NULL,
    ORIGEN VARCHAR(70) NOT NULL,
    ASISTE_ID BIGINT NOT NULL,
    ASISTIDO_ID BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS BLOQUEO (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PARTIDO_ID BIGINT NOT NULL,
    MINUTO BIGINT NOT NULL,
    SEGUNDO BIGINT NOT NULL,
    ORIGEN VARCHAR(70) NOT NULL,
    BLOQUEA_ID BIGINT NOT NULL,
    BLOQUEADO_ID BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS ROBO (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PARTIDO_ID BIGINT NOT NULL,
    MINUTO BIGINT NOT NULL,
    SEGUNDO BIGINT NOT NULL,
    ORIGEN VARCHAR(70) NOT NULL,
    ROBADOR_ID BIGINT NOT NULL,
    PERDEDOR_ID BIGINT NOT NULL
);