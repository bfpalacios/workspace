package pe.gob.produce.produccion.dao;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.gob.produce.produccion.dao.dominio.Curso;

public interface CursoIDao {
	public List<Curso>   listarCursos()  throws Exception;
	public               Curso obtenerCurso(String codigo) throws Exception;
	public List<CursoBO> listarCursosxDocenteRegular(String codDocente) throws Exception;
	public List<CursoBO> listarCursosDocente(String codDocente, int proceso, int modo) throws Exception;
	public List<CursoBO> listarCursosPorAreaConocimiento(String codigoAreaConocimiento);
	public List<CursoBO> listarCursosAprobadosPorAreaConocimiento(String codigoAreaConocimiento,String codigoTutor) throws Exception;

	CursoBO obtenerCursoTema(int codigoTema);
	
	public List<CursoBO> listarCursosPorConvocatoria(Integer idConvocatoria);
}
