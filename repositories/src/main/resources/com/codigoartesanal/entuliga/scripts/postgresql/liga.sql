CREATE TABLE IF NOT EXISTS LIGA (
    ID SERIAL PRIMARY KEY,
    NOMBRE VARCHAR(50) NOT NULL,
    NOMBRE_COMPLETO VARCHAR(70) NOT NULL,
    TELEFONO VARCHAR(70) NOT NULL,
    GEOLOCATION_ID BIGINT NOT NULL,
    ADMIN_ID VARCHAR(25) NOT NULL
);

INSERT INTO LIGA (ID, NOMBRE, NOMBRE_COMPLETO, TELEFONO, GEOLOCATION_ID, ADMIN_ID) VALUES
(1, 'Liga de Mixquiahuala', 'Liga Regional de Mixquiahuala', '7387250554', 1, 'jperez@tu.me'),
(2, 'Liga de Pachuca', 'Liga Occidental de Pachuca', '7711702233', 2, 'jsoto');
