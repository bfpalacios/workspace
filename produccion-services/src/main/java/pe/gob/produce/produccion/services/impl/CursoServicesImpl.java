package pe.gob.produce.produccion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.gob.produce.produccion.dao.CursoIDao;
import pe.gob.produce.produccion.dao.dominio.Curso;
import pe.gob.produce.produccion.dao.jdbc.CursoDAO;
import pe.gob.produce.produccion.services.CursoServices;
import pe.gob.produce.produccion.services.transformer.CursoTransformerToBO;
import pe.gob.produce.produccion.services.transformer.CursoTransformerToENTIDAD;

@Service("cursoServices")
public class CursoServicesImpl implements CursoServices {

	@Autowired
	private CursoIDao cursoIDao;

	@Autowired
	private CursoDAO cursoDAO;

	@Autowired
	private CursoTransformerToBO cursoTransformerToBO;

	@Autowired
	private CursoTransformerToENTIDAD cursoTransformerToENTIDAD;

	public CursoBO obtenerAlumno(String codigo) throws Exception {
		System.out.println("codigo  : " + codigo);
		Curso cursoEntidad = cursoIDao.obtenerCurso(codigo);
		return cursoTransformerToBO.transformer(cursoEntidad);
	}

	public List<CursoBO> listarCursos() throws Exception {
		return cursoTransformerToBO.transformer(cursoIDao.listarCursos());
	}

	public void guardarCursos(List<CursoBO> lista) throws Exception {
		cursoDAO.insertarLista(cursoTransformerToENTIDAD.transformer(lista));
	}

	public List<CursoBO> listarCursosPorTutor(String codTutor) throws Exception {
		return null;
	}

	public List<CursoBO> listarCursosxDocenteRegular(String codDocente) throws Exception {
		return cursoIDao.listarCursosxDocenteRegular(codDocente);
	}

	public List<CursoBO> listarCursosDocente(String codDocente, int proceso, int modo) throws Exception {
		return cursoIDao.listarCursosDocente(codDocente, proceso, modo);
	}

	public List<CursoBO> listarCursosPorAreaConocimiento(String codigoAreaConocimiento) throws Exception {
		return cursoIDao.listarCursosPorAreaConocimiento(codigoAreaConocimiento);
	}

	@Override
	public List<CursoBO> listarCursosAprobadosPorAreaConocimiento(String codigoTutor, String codigoAreaConocimiento) throws Exception {
		return  cursoIDao.listarCursosAprobadosPorAreaConocimiento(codigoTutor, codigoAreaConocimiento);
	}

	@Override
	public CursoBO obtnerCursoTema(int codigoTema) {
		return cursoIDao.obtenerCursoTema(codigoTema);
	}
	
	@Override
	public List<CursoBO> listarCursosPorConvocatoria(Integer idConvocatoria) {
		return cursoIDao.listarCursosPorConvocatoria(idConvocatoria);
	}
}

