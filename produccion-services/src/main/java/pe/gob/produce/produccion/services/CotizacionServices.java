package pe.gob.produce.produccion.services;

import java.util.List;

import pe.gob.produce.produccion.bo.CotizacionBO;
import pe.gob.produce.produccion.bo.CotizacionDTO;
import pe.gob.produce.produccion.bo.ServicioDTO;

public interface CotizacionServices {
	
	public List<CotizacionDTO> lsCotizacionByUsuario(Integer idusuario);

	public int obtenerCodigoCotizacion()  throws Exception;
	public void guardarCotizacion(CotizacionBO cotizacion);
	public List<ServicioDTO> lsServicioByCotizacion(Integer idcotizacion);
	
	public void generarOrdenPago(Integer idcotizacion, String rutacomprobante);
	
	public CotizacionDTO getCotizacion(Integer idcotizacion);
	
	public int obtenerCiteSede(String ubigeo, String nombreCite) throws Exception;
}
