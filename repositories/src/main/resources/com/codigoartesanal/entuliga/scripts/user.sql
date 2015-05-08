CREATE TABLE IF NOT EXISTS USER (
    USERNAME VARCHAR(25) PRIMARY KEY,
    PASSWORD VARCHAR(70) NOT NULL,
    ENABLED BIT NOT NULL
);
               
INSERT INTO USER (USERNAME, PASSWORD, ENABLED) VALUES
('jperez', '123456', 1),
('jsoto', '$2a$10$GqqtbEuDi8YXzI1n8Zoqv.Upp61NP/Jy1fvPiMAgtcsyFuwc7N.AK', 1);

CREATE TABLE IF NOT EXISTS USER_ROLE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(25) NOT NULL,
    ROLE VARCHAR(25) NOT NULL
);

INSERT INTO USER_ROLE (ID, USERNAME, ROLE) VALUES
(1, 'jperez', 'ADMIN'),
(2, 'jsoto', 'GERENTE');