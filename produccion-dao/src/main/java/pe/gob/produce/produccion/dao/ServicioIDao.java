package pe.gob.produce.produccion.dao;

import java.util.List;

import pe.gob.produce.produccion.bo.ServicioBO;

public interface ServicioIDao {
	
	public List<ServicioBO> buscarServicio(String codigo, String nombre, int idCite);
	public List<ServicioBO> buscarServicio();

}
