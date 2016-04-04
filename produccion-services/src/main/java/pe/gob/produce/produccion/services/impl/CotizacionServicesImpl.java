package pe.gob.produce.produccion.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.produce.produccion.bo.CotizacionBO;
import pe.gob.produce.produccion.dao.CotizacionIDAO;
import pe.gob.produce.produccion.services.CotizacionServices;


@Component("cotizacionServices")
public class CotizacionServicesImpl implements CotizacionServices{
	
	
	@Autowired
	private CotizacionIDAO cotizacionDAO;
	
	
	
	@Override
	public int obtenerCodigoCotizacion() throws Exception {
		// TODO Auto-generated method stub
		return cotizacionDAO.obtenerCodigoCotizacion();
	}

	@Override
	public void guardarCotizacion(CotizacionBO cotizacion)
			{
		// TODO Auto-generated method stub
		try {
			cotizacionDAO.guardarCotizacion(cotizacion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int obtenerCiteSede(String ubigeo, String nombreCite)
			throws Exception {
		// TODO Auto-generated method stub
		return cotizacionDAO.obtenerCiteSede(ubigeo, nombreCite);
	}
	
	

}
