CREATE TABLE IF NOT EXISTS GEOLOCATION (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CALLE VARCHAR(50) NOT NULL,
    NO_EXTERIOR VARCHAR(70) NOT NULL,
    NO_INTERIOR VARCHAR(70) NULL,
    CODIGO_POSTAL VARCHAR(70) NOT NULL,
    COLONIA_ID BIGINT NOT NULL,
    LONGITUDE DOUBLE NULL,
    LATITUDE DOUBLE NULL
);