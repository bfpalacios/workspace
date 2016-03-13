package pe.gob.produce.produccion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.produce.produccion.bo.UsuarioBO;
import pe.gob.produce.produccion.core.dao.DAOImpl;
import pe.gob.produce.produccion.core.dao.jdbc.Conexion;
import pe.gob.produce.produccion.dao.dominio.Usuario;




@Repository("usuarioDao")
@Transactional
public class UsuarioDaoImpl extends DAOImpl<Usuario,String> implements UsuarioIDao {

	public Usuario obtenerUsuario(String usuario) throws Exception {
		return super.obtenerEntidadPorId(Usuario.class,usuario);
	}
	
	public void grabarUsuarioObservados(UsuarioBO usuarioNuevo) throws SQLException{
		Connection con = null;
		CallableStatement cstm = null;
		
		con = Conexion.obtenerConexion();
		cstm = con.prepareCall("{call CREAR_USUARIO_OBSERVADOS(?,?,?,?,?,?,?,?,?)}");		
		cstm.setQueryTimeout(3);
		cstm.setString(1, usuarioNuevo.getIdUsuario());		
		cstm.setString(2, usuarioNuevo.getContrasenia());
		cstm.setString(3, usuarioNuevo.getApellidoPaterno());
		cstm.setString(4, usuarioNuevo.getApellidoMaterno());
		cstm.setString(5, usuarioNuevo.getNombres());
		cstm.setString(6, usuarioNuevo.getCorreo());
		cstm.setString(7, usuarioNuevo.getDireccion());
		cstm.setString(8, usuarioNuevo.getTelefono());
		cstm.setInt(9, Integer.parseInt(usuarioNuevo.getIdRol()));
		cstm.execute();
	}

	
	public void grabarUsuarioRegulares(UsuarioBO usuarioNuevo) throws SQLException{
		Connection con = null;
		CallableStatement cstm = null;
		
		con = Conexion.obtenerConexion();
		cstm = con.prepareCall("{call CREAR_USUARIO_REGULARES(?,?,?,?,?,?,?,?,?,?,?)}");		
		cstm.setQueryTimeout(3);
		cstm.setString(1, usuarioNuevo.getIdUsuario());		
		cstm.setString(2, usuarioNuevo.getContrasenia());
		cstm.setString(3, usuarioNuevo.getApellidoPaterno());
		cstm.setString(4, usuarioNuevo.getApellidoMaterno());
		cstm.setString(5, usuarioNuevo.getNombres());
		cstm.setString(6, usuarioNuevo.getCorreo());
		cstm.setString(7, usuarioNuevo.getDireccion());
		cstm.setString(8, usuarioNuevo.getTelefono());		
		cstm.setInt(9, Integer.parseInt(usuarioNuevo.getIdRol()));
		cstm.setString(10, usuarioNuevo.getCodAlumno()==null?"0":usuarioNuevo.getCodAlumno());
		cstm.setInt(11, usuarioNuevo.getPlanAlumno()==0?0:usuarioNuevo.getPlanAlumno());
		cstm.execute();
	}
	
	public String buscarUsuario(String codUsuario) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		
		String codigoUsuario = "";
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call VALIDAR_EXISTENCIA_USUARIO(?,?)}");
			cstm.setString(1, codUsuario);						
			cstm.registerOutParameter(2, OracleTypes.VARCHAR);			
			cstm.execute();
			
			codigoUsuario = cstm.getString(2)==null?"":cstm.getString(2);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return codigoUsuario;
	}
	
	public String buscarUsuarioEquivalencia(String codUsuario) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		
		String codigoUsuarioEquivalencia = "";
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_USUARIO_EQUIVALENCIA(?,?)}");
			cstm.setString(1, codUsuario);						
			cstm.registerOutParameter(2, OracleTypes.VARCHAR);			
			cstm.execute();
			
			codigoUsuarioEquivalencia = cstm.getString(2)==null?"":cstm.getString(2);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return codigoUsuarioEquivalencia;
	}
}
