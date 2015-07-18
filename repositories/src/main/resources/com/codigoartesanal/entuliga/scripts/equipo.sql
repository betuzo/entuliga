CREATE TABLE IF NOT EXISTS EQUIPO (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    ADMIN_ID VARCHAR(25) NOT NULL,
    NOMBRE VARCHAR(50) NOT NULL,
    ALIAS_EQUIPO VARCHAR(70) NOT NULL,
    RUTA_LOGO_EQUIPO VARCHAR(70) NOT NULL
);

INSERT INTO EQUIPO (ID, ADMIN_ID, NOMBRE, ALIAS_EQUIPO, RUTA_LOGO_EQUIPO) VALUES
(1, 'jperez', 'Activos del Bondho', 'Flojos', 'asdasdasdas'),
(2, 'jperez', 'Activados del Mexe', 'Profes', 'asdasdasdas');
