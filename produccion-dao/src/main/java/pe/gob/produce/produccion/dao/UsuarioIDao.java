package pe.gob.produce.produccion.dao;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
import pe.gob.produce.produccion.dao.dominio.Usuario;

public interface UsuarioIDao {
	public Usuario obtenerUsuario(String usuario) throws Exception;
	public String buscarUsuario(String codUsuario) throws Exception;
	public String buscarUsuarioEquivalencia(String codUsuario) throws Exception;
	public void grabarUsuarioRegulares(UsuarioBO usuarioNuevo) throws Exception; 
	public void grabarUsuarioObservados(UsuarioBO usuarioNuevo) throws Exception;

	AlumnoParBO obtenerTutorActividad(int codigoActividad);
}
