alter sequence geolocation_id_seq restart with 3;
INSERT INTO GEOLOCATION (ID, CALLE, NO_EXTERIOR, NO_INTERIOR, CODIGO_POSTAL, COLONIA_ID, LONGITUDE, LATITUDE) VALUES
(1, 'Claro Tapia', '15', null, '42700', 1, 2234234, 2342343),
(2, 'Felipe Angeles', '478', null, '41458', 1, 2234234, 2342343);
