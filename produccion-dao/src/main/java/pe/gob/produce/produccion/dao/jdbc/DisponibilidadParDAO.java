package pe.gob.produce.produccion.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadTutoriaParBO;

public interface DisponibilidadParDAO {

	public List<DisponibilidadTutoriaParBO> getDisponibilidad(Integer idPostulacion);



}
