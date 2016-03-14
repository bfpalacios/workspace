package pe.gob.produce.produccion.bo;

import java.io.Serializable;
import java.util.List;

public class UsuarioBO implements Serializable {

	private static final long serialVersionUID = 1101118440186893647L;
	
	private String idRol;
	private String role;
	private String idUsuario;
	private String contrasenia;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correo;
	private String direccion;
	private String telefono;
	private String codAlumno;
	private int planAlumno;

	private List<RolBO> listRol;

	public UsuarioBO() {
	}

	/**
	 * @return the idRol
	 */
	public String getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol
	 *            the idRol to set
	 */
	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia
	 *            the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres
	 *            the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno
	 *            the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno
	 *            the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo
	 *            the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the codAlumno
	 */
	public String getCodAlumno() {
		return codAlumno;
	}

	/**
	 * @param codAlumno
	 *            the codAlumno to set
	 */
	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}

	/**
	 * @return the planAlumno
	 */
	public int getPlanAlumno() {
		return planAlumno;
	}

	/**
	 * @param planAlumno
	 *            the planAlumno to set
	 */
	public void setPlanAlumno(int planAlumno) {
		this.planAlumno = planAlumno;
	}

	/**
	 * @return the listRol
	 */
	public List<RolBO> getListRol() {
		return listRol;
	}

	/**
	 * @param listRol
	 *            the listRol to set
	 */
	public void setListRol(List<RolBO> listRol) {
		this.listRol = listRol;
	}
}
