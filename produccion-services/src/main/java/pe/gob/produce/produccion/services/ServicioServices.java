package pe.gob.produce.produccion.services;

import java.util.List;

import pe.gob.produce.produccion.bo.ServicioBO;

public interface ServicioServices {
	
	public List<ServicioBO> buscarServicio(String codigo, String nombre, int idCite);
	public List<ServicioBO> buscarServicio();

}
