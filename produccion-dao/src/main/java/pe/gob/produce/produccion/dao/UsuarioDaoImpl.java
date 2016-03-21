package pe.gob.produce.produccion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.produce.produccion.bo.UbigeoBO;
import pe.gob.produce.produccion.bo.UsuarioBO;
import pe.gob.produce.produccion.core.dao.DAOImpl;
import pe.gob.produce.produccion.core.dao.jdbc.BaseDAO;
import pe.gob.produce.produccion.core.dao.jdbc.Conexion;
import pe.gob.produce.produccion.dao.dominio.Usuario;




@Repository("usuarioDao")
@Transactional
public class UsuarioDaoImpl extends DAOImpl<Usuario,String> implements UsuarioIDao {

	public Usuario obtenerUsuario(String usuario) throws Exception {
		Map<String,Object> mapaParametro =  new HashMap<>();
		mapaParametro.put("idUsuario", usuario);
		List<Usuario> listUsuario = super.obtenerListEntidadPorParametro(Usuario.class,mapaParametro);
		if(listUsuario == null){
			return null;
		}
		return listUsuario.get(0);
	}
	
	public void grabarUsuarioClientes(UsuarioBO usuarioNuevo) throws SQLException{
		Connection con = null;
		CallableStatement cstm = null;
		
		con = Conexion.obtenerConexion();
		cstm = con.prepareCall("{call SP_Nuevo_Usuario(?,?,?,?,?,?,?,?,?,?)}");		
		cstm.setQueryTimeout(3);
		cstm.setString(1, usuarioNuevo.getIdUsuario());		
		cstm.setString(2, usuarioNuevo.getContrasenia());
		cstm.setString(3, usuarioNuevo.getNombres());
		cstm.setString(4, usuarioNuevo.getApellidoPaterno());
		cstm.setString(5, usuarioNuevo.getApellidoMaterno());

		cstm.setInt(6, Integer.parseInt(usuarioNuevo.getIdRol()));
		cstm.setString(7, usuarioNuevo.getApellidoMaterno());
		cstm.setString(8, usuarioNuevo.getApellidoMaterno());
		//cstm.setString(6, usuarioNuevo.getCorreo());
		//cstm.setString(7, usuarioNuevo.getDireccion());
		//cstm.setString(8, usuarioNuevo.getTelefono());//en el managementbean adicionarlo
		cstm.setInt(9, Integer.parseInt(usuarioNuevo.getIdRol()));//cambiar por el estado
		cstm.setInt(10, Integer.parseInt(usuarioNuevo.getIdRol()));
		cstm.execute();
	}

	public void grabarUsuarioObservados(UsuarioBO usuarioNuevo) throws SQLException{
		Connection con = null;
		CallableStatement cstm = null;
		
		con = Conexion.obtenerConexion();
		cstm = con.prepareCall("{call SP_Nuevo_Usuario(?,?,?,?,?,?,?,?,?)}");		
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
		//cstm.setInt(11, usuarioNuevo.getPlanAlumno()==0?0:usuarioNuevo.getPlanAlumno());
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
