package pe.gob.produce.produccion.model;

import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioRolBO;

@Component("usuarioModel")
@ViewScoped
public class UsuarioModel {

	private String idUsuario;
	private String clave;
	private String nombres;
	private String paterno;
	private String materno;
	private String correo;
	private String direccion;
	private String telefono;
	private String telefono2;
	
	private String portal;
	private String dni;
	private String tipoDoc;
	private String nroDoc;
	private String rubro;
	private String email1;
	private String email2;
	private String rol;
	private String departamento;
	private String provincia;
	private String perfil;
	private String emailAdmin;	
	private String razonSocial;	
	private String ruc;	
	private String representante;	
	
	
	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	private UsuarioBO usuario;
	private String codAlumno;
	private int planAlumno;
	private UsuarioRolBO usuarioRol;
	private List<UsuarioBO> rolesUsuario;
	private List<ClaseMaestra> listaPlanes;
	
	public UsuarioModel () {
		this.idUsuario = null;
		this.clave = null;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public UsuarioBO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioBO usuario) {
		this.usuario = usuario;
	}
	public UsuarioRolBO getUsuarioRol() {
		return usuarioRol;
	}
	public void setUsuarioRol(UsuarioRolBO usuarioRol) {
		this.usuarioRol = usuarioRol;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public List<UsuarioBO> getRolesUsuario() {
		return rolesUsuario;
	}
	public void setRolesUsuario(List<UsuarioBO> rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
	}
	public String getCodAlumno() {
		return codAlumno;
	}
	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}
	public int getPlanAlumno() {
		return planAlumno;
	}
	public void setPlanAlumno(int planAlumno) {
		this.planAlumno = planAlumno;
	}
	public List<ClaseMaestra> getListaPlanes() {
		return listaPlanes;
	}
	public void setListaPlanes(List<ClaseMaestra> listaPlanes) {
		this.listaPlanes = listaPlanes;
	} 	
}
