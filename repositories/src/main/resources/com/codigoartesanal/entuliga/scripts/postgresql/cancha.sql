CREATE TABLE IF NOT EXISTS CANCHA (
    ID SERIAL PRIMARY KEY,
    ADMIN_ID VARCHAR(25) NOT NULL,
    NOMBRE VARCHAR(50) NOT NULL,
    ALIAS VARCHAR(70) NOT NULL,
    DESCRIPCION VARCHAR(70) NOT NULL,
    GEOLOCATION_ID BIGINT NOT NULL
);

INSERT INTO CANCHA (ID, ADMIN_ID, NOMBRE, ALIAS, DESCRIPCION, GEOLOCATION_ID) VALUES
(1, 'jperez@tu.me', 'El Calvario Uno', 'Calvario 1', 'Techada, con luz, sin grada', 1),
(2, 'jperez@tu.me', 'El Calvario Dos', 'Calvario 2', 'Sin techo, sin luz, sin grada', 2);
