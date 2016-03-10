package pe.gob.produce.produccion.dao;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;

public interface ServicioIDao {
	
	public List<RolBO> buscarServicio(String codigo, String nombre, String rol);
	public List<RolBO> buscarServicio();

}
