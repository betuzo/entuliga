CREATE TABLE IF NOT EXISTS EQUIPO (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    ADMIN_ID VARCHAR(25) NOT NULL,
    NOMBRE VARCHAR(50) NOT NULL,
    ALIAS_EQUIPO VARCHAR(70) NOT NULL,
    RUTA_LOGO_EQUIPO VARCHAR(70) NOT NULL,
    MAIN_COLOR VARCHAR(10) NOT NULL
);