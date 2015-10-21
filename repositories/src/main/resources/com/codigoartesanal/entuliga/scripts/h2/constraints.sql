ALTER TABLE USER_ROLE ADD CONSTRAINT FK3801613117211B01 FOREIGN KEY(USERNAME) REFERENCES USER(USERNAME) NOCHECK;
ALTER TABLE ESTADO ADD CONSTRAINT FK3801613117211B02 FOREIGN KEY(PAIS_ID) REFERENCES PAIS(ID) NOCHECK;
ALTER TABLE MUNICIPIO ADD CONSTRAINT FK3801613117211B03 FOREIGN KEY(ESTADO_ID) REFERENCES ESTADO(ID) NOCHECK;
ALTER TABLE COLONIA ADD CONSTRAINT FK3801613117211B04 FOREIGN KEY(MUNICIPIO_ID) REFERENCES MUNICIPIO(ID) NOCHECK;
ALTER TABLE GEOLOCATION ADD CONSTRAINT FK3801613117211B05 FOREIGN KEY(COLONIA_ID) REFERENCES COLONIA(ID) NOCHECK;
ALTER TABLE EQUIPO ADD CONSTRAINT FK3801613117211B06 FOREIGN KEY(ADMIN_ID) REFERENCES USER(USERNAME) NOCHECK;
ALTER TABLE LIGA ADD CONSTRAINT FK3801613117211B07 FOREIGN KEY(GEOLOCATION_ID) REFERENCES GEOLOCATION(ID) NOCHECK;
ALTER TABLE LIGA ADD CONSTRAINT FK3801613117211B08 FOREIGN KEY(ADMIN_ID) REFERENCES USER(USERNAME) NOCHECK;
ALTER TABLE JUGADOR ADD CONSTRAINT FK3801613117211B09 FOREIGN KEY(ADMIN_ID) REFERENCES USER(USERNAME) NOCHECK;
ALTER TABLE JUGADOR ADD CONSTRAINT FK3801613117211B10 FOREIGN KEY(GEOLOCATION_ID) REFERENCES GEOLOCATION(ID) NOCHECK;
ALTER TABLE CANCHA ADD CONSTRAINT FK3801613117211B11 FOREIGN KEY(GEOLOCATION_ID) REFERENCES GEOLOCATION(ID) NOCHECK;
