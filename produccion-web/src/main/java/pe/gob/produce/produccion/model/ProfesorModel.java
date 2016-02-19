package pe.gob.produce.produccion.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.gob.produce.produccion.dao.dominio.Curso;

@Component("profesorModel")
@RequestScoped
public class ProfesorModel extends PersonaModel{
	
	private Curso curso;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	

}
