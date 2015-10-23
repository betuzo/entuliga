DROP TABLE IF EXISTS ClasificacionView;

Create view ClasificacionView as

SELECT 
	torneoequipo.equipo_id, 
	equipo.nombre, 
	totallocal.torneo_id,

	totallocal.puntos_local, 
	totallocal.puntos_local_contra, 
	totallocal.ganados_local, 
	totallocal.perdidos_local, 
	totallocal.jugados_local, 
	
	totalvisita.puntos_visita, 
	totalvisita.puntos_visita_contra, 
	totalvisita.ganados_visita, 
	totalvisita.perdidos_visita, 
	totalvisita.jugados_visita, 
	
	totallocal.puntos_local + totalvisita.puntos_visita as total_puntos_favor, 
	totallocal.puntos_local_contra + totalvisita.puntos_visita_contra as total_puntos_contra, 
	(totallocal.puntos_local + totalvisita.puntos_visita) - (totallocal.puntos_local_contra + totalvisita.puntos_visita_contra) as diferencia_puntos, 
	totallocal.ganados_local + totalvisita.ganados_visita as total_ganados, 
	totallocal.perdidos_local + totalvisita.perdidos_visita as total_perdidos, 
	totallocal.jugados_local + totalvisita.jugados_visita as total_jugados 
FROM 	
	equipo, 
	torneoequipo, 
	( 
	select 
		l.local_id, 
		j.torneo_id,
		sum(l.puntos_local) as puntos_local, 
		sum(l.puntos_visitante) as puntos_local_contra, 
		count(*) as jugados_local, 
		SUM(CASE WHEN l.puntos_local>l.puntos_visitante THEN 1 ELSE 0 END) as ganados_local, 
		SUM(CASE WHEN l.puntos_visitante>l.puntos_local THEN 1 ELSE 0 END) as perdidos_local 
	from 
		partido l, jornada j 
	where 
		l.jornada_id = j.id 
	group by l.local_id, j.torneo_id
	) totallocal, 
	( 
	select 
		v.visitante_id, 
		j.torneo_id,
		sum(v.puntos_visitante) as puntos_visita, 
		sum(v.puntos_local) as puntos_visita_contra, 
		count(*) as jugados_visita, 
		SUM(CASE WHEN v.puntos_visitante>v.puntos_local THEN 1 ELSE 0 END) as ganados_visita, 
		SUM(CASE WHEN v.puntos_local>v.puntos_visitante THEN 1 ELSE 0 END) as perdidos_visita 
	from 
		partido v, jornada j 
	where 
		v.jornada_id = j.id 
	group by v.visitante_id, j.torneo_id
	) totalvisita 
WHERE 
	equipo.id = torneoequipo.equipo_id 
	and torneoequipo.equipo_id = totallocal.local_id 
	and torneoequipo.equipo_id = totalvisita.visitante_id
ORDER BY
	total_ganados, diferencia_puntos, total_puntos_favor;
