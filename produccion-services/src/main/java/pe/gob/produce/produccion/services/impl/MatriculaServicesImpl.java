package pe.gob.produce.produccion.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;
import pe.gob.produce.produccion.dao.CursoIDao;
import pe.gob.produce.produccion.dao.dominio.Curso;
import pe.gob.produce.produccion.dao.jdbc.MatriculaDAO;
import pe.gob.produce.produccion.services.MatriculaServices;

@Service("matriculaServices") 	
public class MatriculaServicesImpl implements MatriculaServices{
	
	@Autowired
	private MatriculaDAO matriculaDAO;
	@Autowired
	private CursoIDao cursoIDao;

	
	public int  guardarMatriculas(List<MatriculaBO> lista)throws Exception {
		 return  matriculaDAO.insertarLista(lista);
	}
	
	public List<MatriculaBO> obtenerMatriculaAlumnoPorPeriodo(String cod_alumno ,Integer anio ,Integer periodo) throws Exception{
		List<MatriculaBO> listaMatriculas=  new ArrayList<MatriculaBO>();
		Curso cursoEntidad = new Curso();
		listaMatriculas=matriculaDAO.obtenerMatriculaAlumnoPorPeriodo(cod_alumno ,anio , periodo );
		
		for(MatriculaBO mbo : listaMatriculas ){
			cursoEntidad = cursoIDao.obtenerCurso(mbo.getcCodigo());
			mbo.setcNombre(cursoEntidad.getNombre());
		}
		 
		
		return listaMatriculas ;
	}

}
