package pe.gob.produce.produccion.bo;

public class CotizacionBO {
	
	private int codigo;
	private ServicioBO servicio;
	private UsuarioBO usuario;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public ServicioBO getServicio() {
		return servicio;
	}
	public void setServicio(ServicioBO servicio) {
		this.servicio = servicio;
	}
	public UsuarioBO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioBO usuario) {
		this.usuario = usuario;
	}
	
}
