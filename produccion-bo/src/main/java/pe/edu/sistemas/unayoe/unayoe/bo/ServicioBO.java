package pe.edu.sistemas.unayoe.unayoe.bo;

public class ServicioBO {

	private String codigo;
	private String nombre;
	private String unidad;
	private String requisito;
	private String valorDeVenta;
	private String precioDeVenta;
	private CITEBO cite;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getRequisito() {
		return requisito;
	}
	public void setRequisito(String requisito) {
		this.requisito = requisito;
	}
	public String getValorDeVenta() {
		return valorDeVenta;
	}
	public void setValorDeVenta(String valorDeVenta) {
		this.valorDeVenta = valorDeVenta;
	}
	public String getPrecioDeVenta() {
		return precioDeVenta;
	}
	public void setPrecioDeVenta(String precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}
	public CITEBO getCite() {
		return cite;
	}
	public void setCite(CITEBO cite) {
		this.cite = cite;
	}
	
	
	
	
}
