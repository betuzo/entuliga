alter sequence partidoarbitro_id_seq restart with 5;
INSERT INTO partidoarbitro(id, partido_id, torneo_arbitro_id, tipo_arbitro) VALUES (1,1,1,'PRINCIPAL');
INSERT INTO partidoarbitro(id, partido_id, torneo_arbitro_id, tipo_arbitro) VALUES (2,1,2,'ANOTADOR');
INSERT INTO partidoarbitro(id, partido_id, torneo_arbitro_id, tipo_arbitro) VALUES (3,1,3,'CRONOMETRADOR');
INSERT INTO partidoarbitro(id, partido_id, torneo_arbitro_id, tipo_arbitro) VALUES (4,1,4,'AUXILIAR');
