package pe.gob.produce.produccion.dao;


import pe.gob.produce.produccion.bo.CotizacionBO;

public interface CotizacionIDAO {

	
	public int obtenerCodigoCotizacion()  throws Exception;
	public void guardarCotizacion(CotizacionBO cotizacion) throws Exception;
	public int obtenerCiteSede(String ubigeo, String nombreCite) throws Exception;
}
