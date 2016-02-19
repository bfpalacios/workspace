package pe.gob.produce.produccion.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.produce.produccion.core.dao.DAOImpl;
import pe.gob.produce.produccion.dao.dominio.Alumno;

@Repository("alumnoDao")
@Transactional
public class AlumnoDaoImpl extends DAOImpl<Alumno,String> implements AlumnoIDao{
	public void insertarAlumno(Alumno alumno) throws Exception {
		//guarda a  un alumno
		super.insertar(alumno);
	}
	
	
	public Alumno obtenerAlumno(String codigo) throws Exception {
		return super.obtenerEntidadPorId(Alumno.class,codigo);
	}

}
