package pe.gob.produce.produccion.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import pe.gob.produce.produccion.bo.UsuarioBO;
import pe.gob.produce.produccion.core.dao.jdbc.BaseDAO;
import pe.gob.produce.produccion.core.dao.jdbc.Conexion;
import pe.gob.produce.produccion.dao.ComunIDAO;

public class ComunDAOImpl extends BaseDAO implements ComunIDAO{
	
	
	public List<UsuarioBO> obtenerRoles(int proceso){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<UsuarioBO> rolesUsuarios = new ArrayList<UsuarioBO>(); 		
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_ROL(?,?)}");						
			cstm.setInt(1, proceso);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){
				UsuarioBO usuario = new UsuarioBO();
				usuario.setIdRol(String.valueOf(rs.getInt(1)));
				usuario.setRole(rs.getString(2));
				rolesUsuarios.add(usuario);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarCallable(cstm);
			this.cerrarConexion(con);
		}
		return rolesUsuarios;
	}
	
	
}
