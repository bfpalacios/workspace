package pe.gob.produce.produccion.dao;

import java.util.List;
import pe.gob.produce.produccion.bo.CotizacionBO;
import pe.gob.produce.produccion.bo.CotizacionDTO;
import pe.gob.produce.produccion.bo.ServicioDTO;

public interface CotizacionIDAO {

	
	public int obtenerCodigoCotizacion()  throws Exception;
	public void guardarCotizacion(CotizacionBO cotizacion) throws Exception;
	public int obtenerCiteSede(String ubigeo, String nombreCite) throws Exception;
	public List<CotizacionDTO> lsCotizacionByUsuario(Integer idusuario);

	public List<ServicioDTO> lsServicioByCotizacion(Integer idcotizacion);

	public void generarOrdenPago(Integer idcotizacion, String rutacomprobante);
	
	public CotizacionDTO getCotizacion(Integer idcotizacion);
}
