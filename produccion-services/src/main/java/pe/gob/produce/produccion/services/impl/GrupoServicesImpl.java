package pe.gob.produce.produccion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;
import pe.gob.produce.produccion.dao.CursoIDao;
import pe.gob.produce.produccion.dao.jdbc.GrupoDAO;
import pe.gob.produce.produccion.services.GrupoServices;
import pe.gob.produce.produccion.services.transformer.CursoTransformerToBO;
import pe.gob.produce.produccion.services.transformer.CursoTransformerToENTIDAD;

@Service("grupoServices") 	
public class GrupoServicesImpl implements GrupoServices{
	
	@Autowired
	private CursoIDao cursoIDao;
	
	@Autowired
	private GrupoDAO grupoDAO;
	
	@Autowired
	private CursoTransformerToBO cursoTransformerToBO;

	@Autowired
	private CursoTransformerToENTIDAD  cursoTransformerToENTIDAD;
	 
	
	
	
	public void  guardarGrupos(List<GrupoBO> lista)throws Exception {
		grupoDAO.insertarLista(lista);
	}

}
