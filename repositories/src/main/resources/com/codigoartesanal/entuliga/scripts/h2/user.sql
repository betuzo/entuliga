CREATE TABLE IF NOT EXISTS USUARIO (
    USERNAME VARCHAR(25) PRIMARY KEY,
    PASSWORD VARCHAR(70) NOT NULL,
    ENABLED BIT NOT NULL
);
               
INSERT INTO USUARIO (USERNAME, PASSWORD, ENABLED) VALUES
('jperez', '123456', 1),
('jsoto', '$2a$10$GqqtbEuDi8YXzI1n8Zoqv.Upp61NP/Jy1fvPiMAgtcsyFuwc7N.AK', 1),
('rolguin', '123456', 1),
('sgarcia', '123456', 1),
('jmolina', '123456', 1),
('snaranjo', '123456', 1),
('gduque', '123456', 1),
('jsaenz', '123456', 1),
('gloreto', '123456', 1),
('omurillo', '123456', 1),
('aosorno', '123456', 1),
('cpalacio', '123456', 1),
('hgonzalez', '123456', 1),
('cmontoya', '123456', 1),
('atabares', '123456', 1),
('jlopez', '123456', 1);

CREATE TABLE IF NOT EXISTS USER_ROLE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(25) NOT NULL,
    ROLE VARCHAR(25) NOT NULL
);

INSERT INTO USER_ROLE (ID, USERNAME, ROLE) VALUES
(1, 'jperez', 'ADMIN'),
(2, 'jsoto', 'GERENTE'),
(3, 'rolguin', 'JUGADOR'),
(4, 'sgarcia', 'JUGADOR'),
(5, 'jmolina', 'JUGADOR'),
(6, 'snaranjo', 'JUGADOR'),
(7, 'gduque', 'JUGADOR'),
(8, 'jsaenz', 'JUGADOR'),
(9, 'gloreto', 'JUGADOR'),
(10, 'omurillo', 'JUGADOR'),
(11, 'aosorno', 'JUGADOR'),
(12, 'cpalacio', 'JUGADOR'),
(13, 'hgonzalez', 'JUGADOR'),
(14, 'cmontoya', 'JUGADOR'),
(15, 'atabares', 'JUGADOR'),
(16, 'jlopez', 'JUGADOR');