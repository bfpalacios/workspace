package pe.gob.produce.produccion.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;


@Component("cotizacionModel")
@RequestScoped
public class CotizacionModel {

	
	private String codigo;
	private UsuarioModel usuarioModel;
	private ServicioModel servicioModel;
	
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public ServicioModel getServicioModel() {
		return servicioModel;
	}

	public void setServicioModel(ServicioModel servicioModel) {
		this.servicioModel = servicioModel;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
}
