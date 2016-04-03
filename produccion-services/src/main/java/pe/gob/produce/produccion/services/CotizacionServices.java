package pe.gob.produce.produccion.services;

import java.util.List;

import pe.gob.produce.produccion.bo.CotizacionBO;

public interface CotizacionServices {

	public int obtenerCodigoCotizacion()  throws Exception;
	public void guardarCotizacion(List<CotizacionBO> listaCotizacion) throws Exception;
}
