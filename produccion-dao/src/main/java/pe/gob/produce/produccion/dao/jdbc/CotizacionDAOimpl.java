package pe.gob.produce.produccion.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.produce.produccion.bo.CotizacionBO;
import pe.gob.produce.produccion.bo.CotizacionDTO;
import pe.gob.produce.produccion.bo.ServicioDTO;
import pe.gob.produce.produccion.core.dao.jdbc.BaseDAO;
import pe.gob.produce.produccion.core.dao.jdbc.Conexion;
import pe.gob.produce.produccion.dao.CotizacionIDAO;


@Component("cotizacionDao")
@Transactional
public class CotizacionDAOimpl extends BaseDAO implements CotizacionIDAO{
	//@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setConexion(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
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
	
	@Override
	public List<CotizacionDTO> lsCotizacionByUsuario(Integer idusuario) {

		String sql = " SELECT C.IDCOTIZACION, U.IDUSUARIO, U.APELLIDO_PAT + ' ' + U.APELLIDO_MAT + ' ' + U.NOMBRES AS SOLICITANTE, "
				+ " C.FECHA, (SELECT A.NOMBRE_CITE FROM CITE A INNER JOIN CITE_SEDE B ON A.IDCITE=B.IDCITE WHERE B.IDCITE_SEDE = C.IDCITE_SEDE) CITE_DESTINO , "
				+ " C.TOTAL, C.ESTADO FROM COTIZACION C INNER JOIN USUARIO U ON C.IDUSUARIO=U.IDUSUARIO  WHERE U.IDUSUARIO = ?";

		RowMapper rowMapper = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				CotizacionDTO c = new CotizacionDTO();
				c.setNumeroCotizacion("" + rs.getInt("IDCOTIZACION"));
				c.setRazonSocial(rs.getString("IDUSUARIO"));
				c.setSolicitante(rs.getString("SOLICITANTE"));
				c.setFecha(rs.getDate("FECHA"));
				c.setCiteDestino(rs.getString("CITE_DESTINO"));
				c.setCostoTotal(rs.getDouble("TOTAL"));
				c.setEstado(rs.getInt("ESTADO"));
				return c;
			}
		};

		List<CotizacionDTO> ls = jdbcTemplate.query(sql, rowMapper, idusuario);
		return ls;
	}

	@Override
	public List<ServicioDTO> lsServicioByCotizacion(Integer idcotizacion) {

		String sql = " SELECT D.IDDETALLECOTIZACION, S.SERVICIO_DESC, S.VALOR_VENTA, S.PRECIO_VENTA, "
				+ " (SELECT UNIDAD_DESC FROM UNIDAD_SERVICIO WHERE IDUNIDAD_SERVICIO=S.UNIDAD_SERVICIO) AS UNIDAD "
				+ " FROM COTIZACION_DETALLE D INNER JOIN SERVICIO S ON D.IDSERVICIO = S.IDSERVICIO "
				+ " WHERE D.IDCOTIZACION = ?";

		RowMapper rowMapper = new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				ServicioDTO s = new ServicioDTO();
				s.setCodigo(rs.getInt("IDDETALLECOTIZACION"));
				s.setNombreServicio(rs.getString("SERVICIO_DESC"));
				s.setValorVenta(rs.getDouble("VALOR_VENTA"));
				s.setPrecioVenta(rs.getDouble("PRECIO_VENTA"));
				s.setUnidad(rs.getString("UNIDAD"));
				return s;
			}
		};

		List<ServicioDTO> ls = jdbcTemplate.query(sql, rowMapper, idcotizacion);
		return ls;
		
	}

	@Override
	public void generarOrdenPago(Integer idcotizacion, String rutacomprobante) {		
		String sql = "  INSERT INTO ORDEN_PAGO(IDCOTIZACION, COMPROBANTE) VALUES(?, ?)";
        int ctos = jdbcTemplate.update(sql, idcotizacion, rutacomprobante);
	}

	@Override
	public CotizacionDTO getCotizacion(Integer idcotizacion) {
		
		String sql = "  SELECT IDCOTIZACION, ESTADO, FECHA, TOTAL " + 
					 "	FROM COTIZACION WHERE IDCOTIZACION = ? ";
        
        RowMapper mapper = new RowMapper() {
            @Override
            public CotizacionDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
            	CotizacionDTO c = new CotizacionDTO();
                c.setNumeroCotizacion(""+rs.getInt("IDCOTIZACION"));
                c.setEstado(rs.getInt("ESTADO"));
                c.setFecha(rs.getDate("FECHA"));
                c.setCostoTotal(rs.getDouble("TOTAL"));
                return c;
            }
        };

        CotizacionDTO cotizacion = (CotizacionDTO) jdbcTemplate.queryForObject(sql, mapper, idcotizacion);
        return cotizacion;
	}


}
