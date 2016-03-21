package pe.gob.produce.produccion.services;

import java.util.List;

import pe.gob.produce.produccion.bo.UbigeoBO;

public interface ComunServices {
	public List<UbigeoBO> listUbigeo() throws Exception;
	public List<UbigeoBO> listarProvincia(String codDepartamento) throws Exception;
	public List<UbigeoBO> listarDistrito(String codDepartamento, String codProvincia) throws Exception;
	
}
