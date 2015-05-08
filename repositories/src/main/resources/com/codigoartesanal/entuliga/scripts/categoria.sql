CREATE TABLE IF NOT EXISTS CATEGORIA (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CLAVE_CATEGORIA VARCHAR(25) NOT NULL,
    DESCRIPCION VARCHAR(255) NOT NULL
);
               
INSERT INTO CATEGORIA (ID, CLAVE_CATEGORIA, DESCRIPCION) VALUES
(1, 'Generales', 'Servicios generales, planchado, comida, taxi, etc.'),
(2, 'Comodidad', 'Servicios para el descanso y comodidad, almohada, cobertor, calefacción, etc.'),
(3, 'Premium', 'Servicios de gama alta, masajes, facial, spa, etc.');