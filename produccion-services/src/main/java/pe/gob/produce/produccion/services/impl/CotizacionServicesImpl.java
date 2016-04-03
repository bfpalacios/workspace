package pe.gob.produce.produccion.services.impl;

import java.util.List;

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
	public void guardarCotizacion(List<CotizacionBO> listaCotizacion)
			throws Exception {
		// TODO Auto-generated method stub
		cotizacionDAO.guardarCotizacion(listaCotizacion);
	}
	
	

}
