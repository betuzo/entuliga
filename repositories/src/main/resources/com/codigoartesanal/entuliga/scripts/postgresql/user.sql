CREATE TABLE IF NOT EXISTS USUARIO (
    USERNAME VARCHAR(25) PRIMARY KEY,
    PASSWORD VARCHAR(70) NOT NULL,
    ENABLED BIT NOT NULL
);
               
INSERT INTO USUARIO (USERNAME, PASSWORD, ENABLED) VALUES
('jperez@tu.me', '123456', true),
('jsoto', '$2a$10$GqqtbEuDi8YXzI1n8Zoqv.Upp61NP/Jy1fvPiMAgtcsyFuwc7N.AK', true),
('rolguin', '123456', true),
('sgarcia', '123456', true),
('jmolina', '123456', true),
('snaranjo', '123456', true),
('gduque', '123456', true),
('jsaenz', '123456', true),
('gloreto', '123456', true),
('omurillo', '123456', true),
('aosorno', '123456', true),
('cpalacio', '123456', true),
('hgonzalez', '123456', true),
('cmontoya', '123456', true),
('atabares', '123456', true),
('jlopez', '123456', true);

CREATE TABLE IF NOT EXISTS USER_ROLE (
    ID SERIAL PRIMARY KEY,
    USERNAME VARCHAR(25) NOT NULL,
    ROLE VARCHAR(25) NOT NULL
);

INSERT INTO USER_ROLE (ID, USERNAME, ROLE) VALUES
(1, 'jperez@tu.me', 'ADMIN'),
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