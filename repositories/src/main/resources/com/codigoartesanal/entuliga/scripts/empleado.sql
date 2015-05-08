CREATE TABLE IF NOT EXISTS EMPLEADO(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(255) NOT NULL,
    APELLIDO_PATERNO VARCHAR(255) NOT NULL,
    APELLIDO_MATERNO VARCHAR(255) NOT NULL,
    SEXO VARCHAR(20) NOT NULL,
    FECHA_NACIMIENTO TIMESTAMP NOT NULL,
    DIRECCION VARCHAR(255) NOT NULL,
    NO_EXT VARCHAR(255) NOT NULL,
    NO_INT VARCHAR(255) NOT NULL,
    COLONIA VARCHAR(255) NOT NULL,
    MUNICIPIO VARCHAR(255) NOT NULL,
    ESTADO VARCHAR(255) NOT NULL,
    PAIS VARCHAR(255) NOT NULL,
    FECHA_INGRESO TIMESTAMP NOT NULL,
    USERNAME VARCHAR(50) NOT NULL,
    HOTEL_ID BIGINT NOT NULL
);                  
     
INSERT INTO EMPLEADO(ID, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, SEXO, FECHA_NACIMIENTO, DIRECCION,
NO_EXT, NO_INT, COLONIA, MUNICIPIO, ESTADO, PAIS, FECHA_INGRESO, USERNAME, HOTEL_ID) VALUES
(1,'Julio','Perez','Aguilar', 'Masculino', TIMESTAMP '1979-08-31 00:00:00.00','Av. Hidalgo','135',''
,'135','135','135','135',TIMESTAMP '1979-08-31 00:00:00.00','jperez',1),
(2,'Juana','Soto','Cruz', 'Femenino', TIMESTAMP '1979-08-31 00:00:00.00','Av. Hidalgo','135',''
,'135','135','135','135',TIMESTAMP '1979-08-31 00:00:00.00','jsoto',1);