CREATE TABLE IF NOT EXISTS TIPO_HABITACION (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CLAVE_TIPO_HABITACION VARCHAR(25) NOT NULL,
    DESCRIPCION VARCHAR(255) NOT NULL
);
               
INSERT INTO TIPO_HABITACION (ID, CLAVE_TIPO_HABITACION, DESCRIPCION) VALUES
(1, 'Sencilla', STRINGDECODE('Habitaci\u00f3n sencilla con cama matrimonial')),
(2, 'Doble', STRINGDECODE('Habitaci\u00f3n doble con dos camas matrimoniales')),
(3, 'Penthouse', STRINGDECODE('Habitaci\u00f3n penthouse con cama king size, sala y cocina equipada'));