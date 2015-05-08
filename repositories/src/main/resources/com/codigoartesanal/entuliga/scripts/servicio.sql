CREATE TABLE IF NOT EXISTS SERVICIO (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CLAVE_SERVICIO VARCHAR(25) NOT NULL,
    DESCRIPCION VARCHAR(255) NOT NULL
);
               
INSERT INTO SERVICIO (ID, CLAVE_SERVICIO, DESCRIPCION) VALUES
(1, 'Planchado', 'Plancha y burro de planchar a la habitación'),
(2, 'Taxi', 'Servicios de taxi particular'),
(3, 'Almohada', 'Almohada extra'),
(4, 'Cobertor', 'Cobertor extra'),
(5, 'Masajes', 'Masajes'),
(6, 'Facial', 'Facial');