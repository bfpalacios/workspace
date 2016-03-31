package pe.gob.produce.produccion.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;


@Component("cotizacionModel")
@RequestScoped
public class CotizacionModel {

	
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
}
