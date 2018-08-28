package com.codigoartesanal.entuliga.repositories

import groovy.sql.Sql

class ScriptColonia {
    static void main(String[] args) {
        def sql = Sql.newInstance("jdbc:postgresql://ec2-107-21-201-57.compute-1.amazonaws.com:5432/ddg0rtuo0jlusu?sslmode=require",
                "pqqngsfucxwznf", "ef2417171737db74759d8b81e124649962c9cd3b2b45e25d94619c4dc498002d", "org.postgresql.Driver")
        def insertEstado = "INSERT INTO province (id,abreviatura,nombre,pais_id) VALUES (NEXTVAL('estado_id_seq'), 'HGO', ?, 1)"
        def insertMunicipio = "INSERT INTO municipality (id,nombre,nombre_oficial,estado_id) VALUES (NEXTVAL('municipio_id_seq'), ?, ?, ?)"
        def insertColonia = "INSERT INTO town (id,nombre,codigo_postal,municipio_id) VALUES (NEXTVAL('colonia_id_seq'), ?, ?, ?)"

        def file = new File('/Users/betuzo/opt/java/bitware/entuliga/repositories/src/test/resources/coloniasdos.csv')
        file.eachLine { String line ->
            def values = line.split("\\|")
            def estado = sql.firstRow("select id from province where nombre=:nombre", [nombre: values[4]])
            def idEstado = 0
            if (estado == null) {
                sql.execute(insertEstado, [values[4]])
                estado = sql.firstRow("select id from province where nombre=:nombre", [nombre: values[4]])
                idEstado = estado[0]
            } else {
                idEstado = estado[0]
            }
            def idMunicipio = 0
            def municipio = sql.firstRow("select id from municipality where nombre=:nombre and estado_id=:estado_id", [nombre: values[3], estado_id: idEstado])
            if (municipio == null) {
                sql.execute(insertMunicipio, [values[3], values[3], idEstado])
                municipio = sql.firstRow("select id from municipality where nombre=:nombre and estado_id=:estado_id", [nombre: values[3], estado_id: idEstado])
                idMunicipio = municipio[0]
            } else {
                idMunicipio = municipio[0]
            }
            def colonia = sql.firstRow("select id from town where nombre=:nombre and municipio_id=:municipio_id", [nombre: values[1], municipio_id: idMunicipio])
            if (colonia == null) {
                sql.execute(insertColonia, [values[1], values[0], idMunicipio])
            }
            println("$values[4] $values[3] $values[1] $values[0]")
        }
    }
}
