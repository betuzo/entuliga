CREATE TABLE IF NOT EXISTS TORNEOCANCHA (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    TORNEO_ID BIGINT NOT NULL,
    CANCHA_ID BIGINT NOT NULL,
    STATUS_CANCHA VARCHAR(50) NOT NULL
);