package pe.gob.produce.produccion.dao;

import pe.gob.produce.produccion.dao.dominio.Alumno;


public interface AlumnoIDao {

	public void insertarAlumno(Alumno alumno)  throws Exception;
	
	public Alumno obtenerAlumno(String usuario) throws Exception; 
}
