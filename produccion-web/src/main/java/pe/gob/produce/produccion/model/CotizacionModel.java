package pe.gob.produce.produccion.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;


@Component("cotizacionModel")
@RequestScoped
public class CotizacionModel {


	private String secuencial;
	private int codigo;
	private String descripcion;
	private UsuarioModel usuarioModel;
	private ServicioModel servicioModel;
	
	
	
	public int getCodigo() {
		return codigo;
	}

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


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	//En la noche nos comunicamos para darte una mejor explicación. Básicamente tienes:

	1) Buscar la cotizacion.
	2) Ver la cotización.
	3) Cambiar el estado de generado a aprobado o de generado a archivado y guardar ese estado en base de datos en la table cotización
	
	
	
}