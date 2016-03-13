package pe.gob.produce.produccion.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.produce.produccion.bo.ServicioBO;
import pe.gob.produce.produccion.core.dao.jdbc.BaseDAO;
import pe.gob.produce.produccion.core.dao.jdbc.Conexion;
import pe.gob.produce.produccion.dao.ServicioIDao;


@Repository("servicioDAO")
@Transactional
public class ServicioDAO extends BaseDAO implements ServicioIDao  {

	@Override
	public List<ServicioBO> buscarServicio(String codigo, String nombre,
			int idCite) {
		
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<ServicioBO> listaServicio = new ArrayList<ServicioBO>();

		try {
			con = Conexion.obtenerConexion();
			PreparedStatement pstmt = con.prepareStatement("{call dbo.Consulta_Servicios_CITE(?,?,?)}");
			pstmt.setString(1, nombre);
			pstmt.setString(2, codigo);
			pstmt.setInt(3, idCite);
		    rs = pstmt.executeQuery();

		    while (rs.next()) {
		        ServicioBO servicioBO = new ServicioBO(); 
		        System.out.println("SERVICIO " + rs.getString("IDSERVICIO"));
		        servicioBO.setCodigo(rs.getString("IDSERVICIO"));
		        servicioBO.setNombre(rs.getString("SERVICIO_DESC"));
		        servicioBO.setUnidad(rs.getString("UNIDAD_DESC"));
		        servicioBO.setRequisito(rs.getString("REQUISITO"));
		        servicioBO.setValorDeVenta(Float.toString(rs.getFloat("VALOR_VENTA")));
		        servicioBO.setPrecioDeVenta(Float.toString(rs.getFloat("PRECIO_VENTA")));
		        
		        System.out.println("VALOR DE VENTA " + servicioBO.getValorDeVenta());
		        System.out.println("PRECIO DE VENTA " + servicioBO.getPrecioDeVenta());
		        
		        listaServicio.add(servicioBO);
		    }
		      
		     
			return listaServicio;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
		
	}

	@Override
	public List<ServicioBO> buscarServicio() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
