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
	public void guardarCotizacion(List<CotizacionBO> listaCotizacion)
			throws Exception {
		// TODO Auto-generated method stub
		
	} 
	
	

}
