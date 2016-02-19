package pe.gob.produce.produccion.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;

public interface MatriculaServices {
	
	public int  guardarMatriculas(List<MatriculaBO> lista) throws Exception;
	
	public List<MatriculaBO> obtenerMatriculaAlumnoPorPeriodo(String alumno ,Integer anio ,Integer periodo) throws Exception;

}
