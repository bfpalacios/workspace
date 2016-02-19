package pe.gob.produce.produccion.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.gob.produce.produccion.core.transformer.Transformer;
import pe.gob.produce.produccion.dao.dominio.Profesor;


@Component("profesorTransformerToBo")
@Scope("singleton")
public class ProfesorTransformerToBo implements  Transformer<Profesor,ProfesorBO> {
	 

	  
	public ProfesorBO transformer(Profesor profesor) {
		ProfesorBO profesorBO = null;
		if(profesor!=null){
			profesorBO = new ProfesorBO();
			profesorBO.setpCodigo(profesor.getPCodigo().trim());
			profesorBO.setpNombre(profesor.getPNombre().toUpperCase().trim());
			profesorBO.setpApellidos(profesor.getPApellidos().toUpperCase().trim());
			  
		} 
		return profesorBO;
	}

	public List<ProfesorBO> transformer(List<Profesor> lista) {
		List<ProfesorBO> listaProfesorBO = new ArrayList<ProfesorBO>();
		for (Profesor profesor : lista) {
			listaProfesorBO.add(transformer(profesor));
		}
		return listaProfesorBO; 
	}

	 

}
