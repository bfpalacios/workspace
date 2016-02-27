package pe.gob.produce.produccion.model;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.CITEBO;



@Component("serviceModel")
@RequestScoped
public class ServicioModel {
	
	
	private String codigo;
	private String nombre;
	private String descripcion;
	private String nombreSolicitante;
	private String cargo;
	private String telefono;
	private String email;
	private String descripcionCITE;
	private String codigoCITE;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	private List<CITEBO> listarCITE;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescripcionCITE() {
		return descripcionCITE;
	}
	public void setDescripcionCITE(String descripcionCITE) {
		this.descripcionCITE = descripcionCITE;
	}
	public String getCodigoCITE() {
		return codigoCITE;
	}
	public void setCodigoCITE(String codigoCITE) {
		this.codigoCITE = codigoCITE;
	}
	public List<CITEBO> getListarCITE() {
		return listarCITE;
	}
	public void setListarCITE(List<CITEBO> listarCITE) {
		this.listarCITE = listarCITE;
	}
	
	
	
	
}
