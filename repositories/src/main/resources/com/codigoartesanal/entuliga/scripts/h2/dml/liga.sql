alter sequence liga_id_seq restart with 3;
INSERT INTO LIGA (ID, NOMBRE, NOMBRE_COMPLETO, TELEFONO, GEOLOCATION_ID, ADMIN_ID) VALUES
(1, 'Liga de Mixquiahuala', 'Liga Regional de Mixquiahuala', '7387250554', 1, 'jperez@tu.me'),
(2, 'Liga de Pachuca', 'Liga Occidental de Pachuca', '7711702233', 2, 'jsoto');
