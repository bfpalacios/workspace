package pe.gob.produce.produccion.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.produce.produccion.bo.CotizacionBO;
import pe.gob.produce.produccion.bo.CotizacionDTO;
import pe.gob.produce.produccion.bo.ServicioDTO;
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

	@Override
	public List<CotizacionDTO> lsCotizacionByUsuario(Integer idusuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServicioDTO> lsServicioByCotizacion(Integer idcotizacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generarOrdenPago(Integer idcotizacion, String rutacomprobante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CotizacionDTO getCotizacion(Integer idcotizacion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
