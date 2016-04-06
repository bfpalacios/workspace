package pe.gob.produce.produccion.services;

import pe.gob.produce.produccion.bo.CotizacionBO;

public interface CotizacionServices {

	public int obtenerCodigoCotizacion()  throws Exception;
	public void guardarCotizacion(CotizacionBO cotizacion);

	public int obtenerCiteSede(String ubigeo, String nombreCite) throws Exception;
}
