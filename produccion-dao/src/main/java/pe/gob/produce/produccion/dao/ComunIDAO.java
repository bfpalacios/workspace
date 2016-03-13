package pe.gob.produce.produccion.dao;

import java.util.List;

import pe.gob.produce.produccion.bo.UsuarioBO;

public interface ComunIDAO {
	public List<UsuarioBO> obtenerRoles(int proceso) throws Exception;


}
