package pe.gob.produce.produccion.dao;

import java.util.List;

import pe.gob.produce.produccion.bo.UbigeoBO;
import pe.gob.produce.produccion.bo.UsuarioBO;

public interface ComunIDAO {
	public List<UsuarioBO> obtenerRoles(int proceso) throws Exception;
	
	public List<UbigeoBO> listUbigeo() throws Exception;
	public List<UbigeoBO> listarProvincia(String codDepartamento) throws Exception;
	public List<UbigeoBO> listarDistrito(String codDepartamento, String codProvincia) throws Exception;
	

}
