alter sequence geolocation_id_seq restart with 4;
INSERT INTO GEOLOCATION (ID, CALLE, NO_EXTERIOR, NO_INTERIOR, COLONIA_ID, LONGITUDE, LATITUDE) VALUES
(1, 'Claro Tapia', '15', null, 1, 124.3516731, 11.3331243),
(2, 'Felipe Angeles', '478', null, 1, 124.3516731, 11.3331243),
(3, 'Maple Wood', '1646', '04324', 3, 124.3516731, 11.3331243);