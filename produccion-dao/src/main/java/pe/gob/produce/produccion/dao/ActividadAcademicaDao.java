package pe.gob.produce.produccion.dao;


import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.HorarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionParBO;

public interface ActividadAcademicaDao {

	boolean guardarActividadAcademica(ActividadAcademicaBO actividadAcademica, List<HorarioBO> horarios,
			List<SesionParBO> sesiones);

	HorarioBO guardarHorarioActividad(HorarioBO horarioBO);

	SesionParBO guardarSesionPar(SesionParBO sesionParBO);

	List<ActividadAcademicaBO> listarActividades();

	ActividadAcademicaBO obtenerActividad(int codigoActividad);

	List<ActividadAcademicaBO> listarActividadesPorTutor(String codTutor);

	List<HorarioBO> horariosPorActividad(int codigoActividad);

	List<SesionParBO> sesionesPorActividad(int codigoActividad);

	List<ActividadAcademicaBO> listarActividadesPorAlumno(String codAlumno);

	List<ActividadAcademicaBO> listarActividadesPorAlumnoMatriculado(String codAlumno);

}
