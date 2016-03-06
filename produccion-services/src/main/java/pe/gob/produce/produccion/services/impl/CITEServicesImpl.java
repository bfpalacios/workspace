package pe.gob.produce.produccion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.unayoe.bo.CITEBO;
import pe.gob.produce.produccion.dao.CITEIDAO;
import pe.gob.produce.produccion.dao.CursoIDao;
import pe.gob.produce.produccion.services.CITEServices;

@Service("citeServices")
public class CITEServicesImpl implements CITEServices{
	
	
	@Autowired
	private CITEIDAO citeIDao;
	
	
	@Override
	public List<CITEBO> listarCITES() throws Exception {

		return citeIDao.listarCites();
	}
	
	

}
