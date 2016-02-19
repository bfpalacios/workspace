package pe.gob.produce.produccion.dao;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.gob.produce.produccion.dao.dominio.Profesor;

public interface TutoriaIDao {	
	public List<Profesor> listarProfesores()  throws Exception;	
	public List<CursoBO> listarCursosDocente(String usuarioDocente) throws Exception;	
}
