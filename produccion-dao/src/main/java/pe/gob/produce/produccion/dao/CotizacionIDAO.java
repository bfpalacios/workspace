package pe.gob.produce.produccion.dao;

import java.util.List;

import pe.gob.produce.produccion.bo.CotizacionBO;

public interface CotizacionIDAO {

	
	public int obtenerCodigoCotizacion()  throws Exception;
	public void guardarCotizacion(List<CotizacionBO> listaCotizacion) throws Exception;
}
