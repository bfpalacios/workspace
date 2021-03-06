package pe.gob.produce.produccion.services;

import java.util.List;

import pe.gob.produce.produccion.bo.MuestraBO;
import pe.gob.produce.produccion.bo.UbigeoBO;
import pe.gob.produce.produccion.bo.UsuarioBO;

public interface ComunServices {
	public List<UbigeoBO> listUbigeo() throws Exception;
	public List<UbigeoBO> listarProvincia(String codDepartamento) throws Exception;
	public List<UbigeoBO> listarDistrito(String codDepartamento, String codProvincia) throws Exception;
	public List<MuestraBO> listarMuestra() throws Exception;
	public UsuarioBO buscarUsuario(String codUsuario)  throws Exception;
	
}
