package pe.gob.produce.produccion.services;

import pe.gob.produce.produccion.bo.CotizacionBO;
import pe.gob.produce.produccion.bo.CotizacionDetalleBO;

public interface CotizacionServices {

	public int obtenerCodigoCotizacion()  throws Exception;
	public int guardarCotizacion(CotizacionBO cotizacion);
	public void guardarCotizacionDetalle(CotizacionDetalleBO cotizacionDetalle);

	public int obtenerCiteSede(String ubigeo, String nombreCite) throws Exception;
}
