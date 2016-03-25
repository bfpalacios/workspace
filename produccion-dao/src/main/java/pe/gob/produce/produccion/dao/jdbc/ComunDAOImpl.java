package pe.gob.produce.produccion.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import oracle.jdbc.OracleTypes;
import pe.gob.produce.produccion.bo.CITEBO;
import pe.gob.produce.produccion.bo.MuestraBO;
import pe.gob.produce.produccion.bo.UbigeoBO;
import pe.gob.produce.produccion.bo.UsuarioBO;
import pe.gob.produce.produccion.core.dao.jdbc.BaseDAO;
import pe.gob.produce.produccion.core.dao.jdbc.Conexion;
import pe.gob.produce.produccion.dao.ComunIDAO;

@Component("comunDao")
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
	
	@Override
	public List<UbigeoBO> listUbigeo() throws Exception {
		
			Connection con = null;
			
			Statement statement = null;
			ResultSet rs = null;
			List<UbigeoBO> listaUbigeos = new ArrayList<UbigeoBO>();
			
			try{
				con = Conexion.obtenerConexion();
				PreparedStatement pstmt = con.prepareStatement("{call dbo.ListarDepartamentos}");
				rs = pstmt.executeQuery();
				
				while(rs.next()){				
					UbigeoBO ubigeo = new UbigeoBO();
					ubigeo.setIdUbigeo(rs.getString(1));
					ubigeo.setDepartamento(rs.getString(2));
					//ubigeo.setProvincia(rs.getString(3));
					//ubigeo.setDistrito(rs.getString(4));
					listaUbigeos.add(ubigeo);
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				this.cerrarResultSet(rs);
				this.cerrarSentenceStatement(statement);
				this.cerrarConexion(con);
			}		
			return listaUbigeos;
		}

	@Override
	public List<UbigeoBO> listarProvincia(String codDepartamento)
			throws Exception {
		Connection con = null;
		
		Statement statement = null;
		ResultSet rs = null;
		List<UbigeoBO> listaUbigeos = new ArrayList<UbigeoBO>();
		
		try{
			con = Conexion.obtenerConexion();
			PreparedStatement pstmt = con.prepareStatement("{call dbo.ListarProvincias(?)}");
			pstmt.setString(1, codDepartamento);
		    rs = pstmt.executeQuery();
			while(rs.next()){				
				UbigeoBO ubigeo = new UbigeoBO();
				ubigeo.setIdUbigeo(rs.getString(1));
				//ubigeo.setDepartamento(rs.getString(2));
				ubigeo.setProvincia(rs.getString(2));
				//ubigeo.setDistrito(rs.getString(4));
				listaUbigeos.add(ubigeo);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarSentenceStatement(statement);
			this.cerrarConexion(con);
		}		
		return listaUbigeos;
	}

	@Override
	public List<UbigeoBO> listarDistrito(String codDepartamento,
			String codProvincia) throws Exception {
		Connection con = null;
		
		Statement statement = null;
		ResultSet rs = null;
		List<UbigeoBO> listaUbigeos = new ArrayList<UbigeoBO>();
		
		try{
			con = Conexion.obtenerConexion();
			PreparedStatement pstmt = con.prepareStatement("{call dbo.ListarDistritos(?,?)}");
			pstmt.setString(1, codDepartamento);
			pstmt.setString(2, codProvincia);
		    rs = pstmt.executeQuery();
			
			while(rs.next()){				
				UbigeoBO ubigeo = new UbigeoBO();
				ubigeo.setIdUbigeo(rs.getString(1));
				//ubigeo.setDepartamento(rs.getString(2));
				//ubigeo.setProvincia(rs.getString(3));
				ubigeo.setDistrito(rs.getString(2));
				listaUbigeos.add(ubigeo);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarSentenceStatement(statement);
			this.cerrarConexion(con);
		}		
		return listaUbigeos;
	}

	@Override
	public List<MuestraBO> listarMuestra() throws Exception {
		
		Connection con = null;
		
		Statement statement = null;
		ResultSet rs = null;
		List<MuestraBO> listaMuestras = new ArrayList<MuestraBO>();
		
		try{
			con = Conexion.obtenerConexion();
			PreparedStatement pstmt = con.prepareStatement("{call dbo.ListarUnidadServicio}");
			rs = pstmt.executeQuery();
			
			while(rs.next()){				
				MuestraBO muestra = new MuestraBO();
				muestra.setCodigo(rs.getString(1));
				muestra.setDescripcion(rs.getString(2));
				listaMuestras.add(muestra);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarSentenceStatement(statement);
			this.cerrarConexion(con);
		}		
		return listaMuestras;
	}
}
