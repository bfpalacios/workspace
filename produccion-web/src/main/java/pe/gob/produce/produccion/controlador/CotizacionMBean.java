package pe.gob.produce.produccion.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.gob.produce.produccion.bo.ServicioBO;
import pe.gob.produce.produccion.core.util.Convertidor;
import pe.gob.produce.produccion.core.util.FormateadorFecha;
import pe.gob.produce.produccion.model.CotizacionModel;
import pe.gob.produce.produccion.model.ServicioModel;
import pe.gob.produce.produccion.services.CITEServices;
import pe.gob.produce.produccion.services.ComunServices;
import pe.gob.produce.produccion.services.ServicioServices;

@Controller("cotizacionMBean")
@ViewScoped
public class CotizacionMBean {

	// ctrl + shit + o importas todas las clases que estan otros paquetes
	@Autowired
	private ServicioModel servicioModel;

	@Autowired
	private CotizacionModel cotizacionModel;

	@Autowired
	private CITEServices citeServices;

	@Autowired
	private ComunServices comunServices;

	@Autowired
	private ServicioServices servicioServices;

	// datos complementarios de la pantalla
	private Date date;

	// constantes
	private int MODO_USUARIO;
	private static int MODO_ADMIN = 1;
	private static int MODO_EMPLEADO = 2;

	// para la lista de servicios se declara una variable list de tipo
	// ServicioModel
	private List<ServicioModel> datosServiciosModelGrid;
	private List<ServicioModel> selectedServicios;

	private Double totalSuma;

	// Constructor
	public CotizacionMBean() {
		System.out.println("::::: LOADING ServicioMBean ::::::::");
		inicializarClases();
		new Convertidor();
		new FormateadorFecha();
		date = new Date();
		this.totalSuma = 0.0;
	}

	public Double getTotalSuma() {
		return totalSuma;
	}

	public void setTotalSuma(Double totalSuma) {
		this.totalSuma = totalSuma;
	}

	private void inicializarClases() {
		this.servicioModel = new ServicioModel();

	}

	public String selectorNuevaCotizacion(int modo) throws Exception {
		String pagina = "";
		inicializarClases();
		List<ServicioBO> listaServicio = new ArrayList<ServicioBO>();

		List<ServicioModel> datosServiciosModelGrid = new ArrayList<ServicioModel>();

		switch (modo) {
		case 1:
			// SE ENVIA 0 EN EL CODIGO DE CITE PARA QUE NOS OBTENGA TODOS LOS
			// SERVICIOS DE LOS CITES
			listaServicio = servicioServices.buscarServicio("", "", 0);

			for (ServicioBO servicioBO : listaServicio) {
				ServicioModel servicioModel = new ServicioModel();
				servicioModel.setCodigo(servicioBO.getCodigo());
				servicioModel.setNombre(servicioBO.getNombre());
				servicioModel.setUnidad(servicioBO.getUnidad());
				servicioModel.setRequisito(servicioBO.getRequisito());
				servicioModel.setValorDeVenta(servicioBO.getValorDeVenta());
				servicioModel.setPrecioDeVenta(servicioBO.getPrecioDeVenta());

				datosServiciosModelGrid.add(servicioModel);
			}

			setCotizacionModel(new CotizacionModel());
			getCotizacionModel().setCodigo("001 - 2016");
			setDatosServiciosModelGrid(datosServiciosModelGrid);
			listarCITE();

			pagina = "/paginas/ModuloProduccion/cliente/cotizacion/nuevo/nuevaCotizacion.xhtml";
			break;

		/* @@ESTE ES EL CASO PARA PERFIL CITE */
		case 2:

			// SE ENVIA 0 EN EL CODIGO DE CITE PARA QUE NOS OBTENGA TODOS LOS
			// SERVICIOS DE LOS CITES
			listaServicio = servicioServices.buscarServicio("", "", 0);

			for (ServicioBO servicioBO : listaServicio) {
				ServicioModel servicioModel = new ServicioModel();
				servicioModel.setCodigo(servicioBO.getCodigo());
				servicioModel.setNombre(servicioBO.getNombre());
				servicioModel.setUnidad(servicioBO.getUnidad());
				servicioModel.setRequisito(servicioBO.getRequisito());
				servicioModel.setValorDeVenta(servicioBO.getValorDeVenta());
				servicioModel.setPrecioDeVenta(servicioBO.getPrecioDeVenta());

				datosServiciosModelGrid.add(servicioModel);
			}

			setDatosServiciosModelGrid(datosServiciosModelGrid);
			listarCITE();
			pagina = "/paginas/ModuloProduccion/cite/servicio/nuevo/nuevoServicio.xhtml";
			break;

		/* @@ESTE ES EL CASO PARA PERFIL EMPRESA */
		case 3:

			// SE ENVIA 0 EN EL CODIGO DE CITE PARA QUE NOS OBTENGA TODOS LOS
			// SERVICIOS DE LOS CITES
			listaServicio = servicioServices.buscarServicio("", "", 0);

			for (ServicioBO servicioBO : listaServicio) {
				ServicioModel servicioModel = new ServicioModel();
				servicioModel.setCodigo(servicioBO.getCodigo());
				servicioModel.setNombre(servicioBO.getNombre());
				servicioModel.setUnidad(servicioBO.getUnidad());
				servicioModel.setRequisito(servicioBO.getRequisito());
				servicioModel.setValorDeVenta(servicioBO.getValorDeVenta());
				servicioModel.setPrecioDeVenta(servicioBO.getPrecioDeVenta());

				datosServiciosModelGrid.add(servicioModel);
			}

			setCotizacionModel(new CotizacionModel());
			getCotizacionModel().setCodigo("001 - 2016");
			setDatosServiciosModelGrid(datosServiciosModelGrid);
			listarCITE();

			pagina = "/paginas/ModuloProduccion/empresa/cotizacion/nuevo/nuevaCotizacion.xhtml";
			break;

		}
		return pagina;
	}

	public void buscarServicio() throws Exception {

		/*
		 * FacesContext facesContext = FacesContext.getCurrentInstance();
		 * LoginModel login = (LoginModel)
		 * facesContext.getExternalContext().getSessionMap().get("user");
		 * System.out.println("login user " + login.getUsuario() + "hola" +
		 * getServicioModel().getCodigoCITE() + "que tal");
		 */
		String nombreServicio = getServicioModel().getNombre() == "" ? null
				: getServicioModel().getNombre();
		String codigoServicio = getServicioModel().getCodigo() == "" ? null
				: getServicioModel().getCodigo();
		String codigoCITE = getServicioModel().getCodigoCITE() == "" ? "0"
				: getServicioModel().getCodigoCITE();

		if (getServicioModel().getCodigoCITE() == null) {
			codigoCITE = "0";

		}

		System.out.println("dATOS SERVICIO BUSQUEDA " + nombreServicio + "-"
				+ codigoServicio + "-" + codigoCITE);

		List<ServicioBO> listaServicio = new ArrayList<ServicioBO>();
		// SE ENVIA EL 6 POR DEFAULT
		listaServicio = servicioServices.buscarServicio(codigoServicio,
				nombreServicio, Integer.parseInt(codigoCITE));
		List<ServicioModel> datosServiciosModelGrid = new ArrayList<ServicioModel>();

		for (ServicioBO servicioBO : listaServicio) {
			ServicioModel servicioModel = new ServicioModel();
			servicioModel.setCodigo(servicioBO.getCodigo());
			servicioModel.setNombre(servicioBO.getNombre());
			servicioModel.setUnidad(servicioBO.getUnidad());
			servicioModel.setRequisito(servicioBO.getRequisito());
			servicioModel.setValorDeVenta(servicioBO.getValorDeVenta());
			servicioModel.setPrecioDeVenta(servicioBO.getPrecioDeVenta());

			datosServiciosModelGrid.add(servicioModel);
		}

		setDatosServiciosModelGrid(datosServiciosModelGrid);
		listarCITE();
	}

	public String cancelar() throws Exception {
		String pagina = "";

		inicializarClases();

		listarCITE();
		pagina = "/paginas/ModuloProduccion/cliente/cotizacion/nuevo/nuevaCotizacion.xhtml";

		return pagina;
	}

	public String verCotizacion() throws Exception {
		String pagina = "";

		pagina = "/paginas/ModuloProduccion/cliente/cotizacion/nuevo/verCotizacion.xhtml";

		return pagina;
	}

	public String selectorBuscarCotizacionServicio(int modo) throws Exception {
		String pagina = "";

		switch (modo) {
		case 1:
			MODO_USUARIO = MODO_ADMIN;
			inicializarClases();

			listarCITE();
			pagina = "/paginas/ModuloProduccion/cliente/cotizacion/buscar/buscarCotizacion.xhtml";
			break;
		/*
		 * @@ESTE ES EL CASO PARA PERFIL EMPLEADO case 2: MODO_USUARIO =
		 * MODO_OCAA; inicializarClases(); if(getDatosAlumnoExcelModelGrid() !=
		 * null){
		 * getDatosAlumnoExcelModelGrid().removeAll(getDatosAlumnoExcelModelGrid
		 * ()); } pagina =
		 * "/paginas/ModuloObservados/ocaa/cargar/cargarDatosAlumnosObs.xhtml";
		 * break;
		 */
		}
		return pagina;
	}

	private void limpiarObjetos() {
		setServicioModel(null);
		setServicioModel(new ServicioModel());
	}

	private void listarCITE() {
		try {

			getServicioModel().setListarCITE(citeServices.listarCITES());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mostrarMensaje(int opcionMensaje) {
		FacesMessage message = null;

		switch (opcionMensaje) {
		case 1:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo caracteres en el campo - " + "Nombres");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 2:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo caracteres en el campo - "
							+ "Apellido Paterno");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 3:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo caracteres en el campo - "
							+ "Apellido Materno");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 4:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar un correo válido en el campo - " + "Correo");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 5:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo números en el campo - " + "Teléfono");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 6:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo números en el campo - "
							+ "Código del alumno");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 7:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					"El usuario ingresado ya ha sido registrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 8:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"El servicio se guardo correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 9:
			message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "",
					"Hubo un error al guardar el usuario");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		}
	}

	public void setServicioModel(ServicioModel servicioModel) {
		this.servicioModel = servicioModel;
	}

	public ServicioModel getServicioModel() {
		return servicioModel;
	}

	public CotizacionModel getCotizacionModel() {
		return cotizacionModel;
	}

	public void setCotizacionModel(CotizacionModel cotizacionModel) {
		this.cotizacionModel = cotizacionModel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ServicioModel> getSelectedServicios() {
		this.totalSuma = DoubleDosDecimales(this.totalSuma);
		return selectedServicios;
	}

	public Double DoubleDosDecimales(Double total){
		total = (double) Math.round(total * 100);
		total = total / 100;
		return total;
	}
	
	public void retirarServicio(ServicioModel servicioModel){
		selectedServicios.remove(servicioModel);
		this.totalSuma = this.totalSuma - DoubleDosDecimales(Double.parseDouble(servicioModel.getPrecioDeVenta()) );
	}
	
	public void setSelectedServicios(List<ServicioModel> selectedServicios) {
		this.totalSuma = 0.0;
		for (int i = 0; i < selectedServicios.size(); i++) {
			this.totalSuma = this.totalSuma + DoubleDosDecimales(Double.parseDouble(selectedServicios.get(i).getPrecioDeVenta()));
		}

		this.totalSuma = DoubleDosDecimales(this.totalSuma);
		this.selectedServicios = selectedServicios;
	}

	public List<ServicioModel> getDatosServiciosModelGrid() {
		return datosServiciosModelGrid;
	}

	public void setDatosServiciosModelGrid(
			List<ServicioModel> datosServiciosModelGrid) {
		this.datosServiciosModelGrid = datosServiciosModelGrid;
	}

}
