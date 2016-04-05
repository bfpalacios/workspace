package com.empresa.proyecto.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.empresa.proyecto.dao.CotizacionDAO;

@Repository
public class CotizacionDAOImpl implements CotizacionDAO {

	// IoC
	private JdbcTemplate jdbcTemplate;

	public void setConexion(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*@Override
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
	}*/

}
