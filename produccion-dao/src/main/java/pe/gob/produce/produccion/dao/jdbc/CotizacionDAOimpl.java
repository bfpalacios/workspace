package pe.gob.produce.produccion.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Component;

import pe.gob.produce.produccion.bo.CotizacionBO;
import pe.gob.produce.produccion.core.dao.jdbc.BaseDAO;
import pe.gob.produce.produccion.core.dao.jdbc.Conexion;
import pe.gob.produce.produccion.dao.CotizacionIDAO;


@Component("cotizacionDao")
public class CotizacionDAOimpl extends BaseDAO implements CotizacionIDAO{

	@Override
	public int obtenerCodigoCotizacion() throws Exception {
		
		int codigoCotizacion =-1;
		Connection con = null;
		CallableStatement cstm = null;
		
		con = Conexion.obtenerConexion();
		cstm = con.prepareCall("{call SP_Obtener_Nro_Cotizacion(?)}");		
		cstm.setQueryTimeout(3);
		cstm.registerOutParameter(1, java.sql.Types.INTEGER);
		
		cstm.execute();
		codigoCotizacion = cstm.getInt(1);
		System.out.println("codigo de cotizacion " + codigoCotizacion);
		return codigoCotizacion;
			
	
	}
	
	@Override
	public int obtenerCiteSede(String ubigeo, String nombreCite) throws Exception {
		
		int codigoCiteSede =-1;
		Connection con = null;
		CallableStatement cstm = null;
		
		con = Conexion.obtenerConexion();
		cstm = con.prepareCall("{call SP_Obtener_CiteSede(?,?,?)}");		
		cstm.setQueryTimeout(3);
		cstm.setString(1, ubigeo);		
		cstm.setString(2, nombreCite);		
		cstm.registerOutParameter(3, java.sql.Types.INTEGER);
		
		cstm.execute();
		codigoCiteSede = cstm.getInt(3);
		System.out.println("codigo de cite sede " + codigoCiteSede);
		return codigoCiteSede;
			
	
	}

	@Override
	public void guardarCotizacion(CotizacionBO cotizacion)
			throws Exception {
		
		Connection con = null;
		CallableStatement cstm = null;
		try {
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call SP_Nueva_Cotizacion(?,?,?,?,?,?)}");	
			System.out.println("codigo de COTIZACION " + cotizacion.getCodigo());
			System.out.println("ID DE USUARIO " + cotizacion.getUsuario().getIdUsuario());
			System.out.println("codigo de servicio " + cotizacion.getServicio().getCodigo());
			System.out.println("cotizacion sede " + cotizacion.getSede());
			System.out.println("SECUENCIAL " + cotizacion.getSecuencial());
			System.out.println("estado " + cotizacion.getEstado());
			cstm.setQueryTimeout(3);
			cstm.setInt(1, cotizacion.getCodigo());		
			cstm.setInt(2, Integer.parseInt(cotizacion.getUsuario().getIdUsuario()));		
			cstm.setString(3, cotizacion.getServicio().getCodigo());		
			cstm.setInt(4, cotizacion.getSede());		
			cstm.setString(5, cotizacion.getSecuencial());
			cstm.setInt(6, cotizacion.getEstado());		
			
			cstm.execute();
		
		
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}

		
	} 
	
	

}
