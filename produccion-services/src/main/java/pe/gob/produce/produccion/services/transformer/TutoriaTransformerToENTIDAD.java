package pe.gob.produce.produccion.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.gob.produce.produccion.core.transformer.Transformer;
import pe.gob.produce.produccion.dao.dominio.Tutoria;
import pe.gob.produce.produccion.dao.dominio.TutoriaPK;

@Component("tutoriaTransformerToENTIDAD")
@Scope("singleton")
public class TutoriaTransformerToENTIDAD implements Transformer<TutoriaBO,Tutoria> {

	public Tutoria transformer(final TutoriaBO tutoriaBO) {
		Tutoria tutoria = new Tutoria();
		TutoriaPK tutoriaok= new TutoriaPK(     );
		if(tutoriaBO!=null){
		// falta
			 
		}
		return tutoria;
	}
	 
	

	public List<Tutoria> transformer(final List<TutoriaBO> lista) {
		List<Tutoria> listaAlumno = new ArrayList<Tutoria>();
		for (TutoriaBO alumnoBO : lista) {
			listaAlumno.add(transformer(alumnoBO));
		}
		return listaAlumno;
	}

}
