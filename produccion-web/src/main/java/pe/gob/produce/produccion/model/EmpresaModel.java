package pe.gob.produce.produccion.model;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@Component("empresaModel")
@ViewScoped
public class EmpresaModel {
	
	private String razonSocial;
	private String ruc;
	private String representante;
	private String portalWeb;
	private String nombreContacto;
	private String nombreCargo;
	
	//constructor por defecto
	public EmpresaModel() {
		
	}
	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return the ruc
	 */
	public String getRuc() {
		return ruc;
	}
	/**
	 * @param ruc the ruc to set
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	/**
	 * @return the representante
	 */
	public String getRepresentante() {
		return representante;
	}
	/**
	 * @param representante the representante to set
	 */
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	/**
	 * @return the portalWeb
	 */
	public String getPortalWeb() {
		return portalWeb;
	}
	/**
	 * @param portalWeb the portalWeb to set
	 */
	public void setPortalWeb(String portalWeb) {
		this.portalWeb = portalWeb;
	}
	/**
	 * @return the nombreContacto
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}
	/**
	 * @param nombreContacto the nombreContacto to set
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	/**
	 * @return the nombreCargo
	 */
	public String getNombreCargo() {
		return nombreCargo;
	}
	/**
	 * @param nombreCargo the nombreCargo to set
	 */
	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}
	
}
