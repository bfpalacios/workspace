package pe.gob.produce.produccion.controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.gob.produce.produccion.bo.CotizacionBO;
import pe.gob.produce.produccion.bo.EmpresaBO;
import pe.gob.produce.produccion.bo.ServicioBO;
import pe.gob.produce.produccion.bo.UsuarioBO;
import pe.gob.produce.produccion.core.util.Convertidor;
import pe.gob.produce.produccion.core.util.FormateadorFecha;
import pe.gob.produce.produccion.core.util.ObtenerNumeroAleatorio;
import pe.gob.produce.produccion.model.CotizacionModel;
import pe.gob.produce.produccion.model.EmpresaModel;
import pe.gob.produce.produccion.model.LoginModel;
import pe.gob.produce.produccion.model.ServicioModel;
import pe.gob.produce.produccion.model.UsuarioModel;
import pe.gob.produce.produccion.services.CITEServices;
import pe.gob.produce.produccion.services.ComunServices;
import pe.gob.produce.produccion.services.CotizacionServices;
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
	private CotizacionServices cotizacionServices;

	@Autowired
	private ComunServices comunServices;

	@Autowired
	private ServicioServices servicioServices;

	// datos complementarios de la pantalla
	private Date date;

	// constantes
	

	// para la lista de servicios se declara una variable list de tipo
	// ServicioModel
	private List<ServicioModel> datosServiciosModelGrid;
	private List<ServicioModel> selectedServicios;
	private List<ServicioModel> servicioCotizacion;

	private Double totalSuma;
	private Double totalSumaCotizar;

	// Constructor
	public CotizacionMBean() {
		System.out.println("::::: LOADING ServicioMBean ::::::::");
		inicializarClases();
		new Convertidor();
		new FormateadorFecha();
		date = new Date();
		this.totalSuma = 0.0;
		this.totalSumaCotizar = 0.0;
	}

	private void inicializarClases() {
		this.servicioModel = new ServicioModel();
		setServicioModel(new ServicioModel());
		setCotizacionModel(new CotizacionModel());
		this.cotizacionModel = new CotizacionModel();
	}

	public String selectorNuevaCotizacion(int modo) throws Exception {
		String pagina = "";
		ObtenerNumeroAleatorio numero = new ObtenerNumeroAleatorio();
		FormateadorFecha fecha = new FormateadorFecha();
		
		inicializarClases();
		
		List<ServicioBO> listaServicio = new ArrayList<ServicioBO>();

		List<ServicioModel> datosServiciosModelGrid = new ArrayList<ServicioModel>();
		
		int codigo = cotizacionServices.obtenerCodigoCotizacion();
		
		if (codigo == 0){
			codigo = 1;
			getCotizacionModel().setDescripcion("000"+ String.valueOf(codigo) +"-" + fecha.obtenerFechaAnio(new Date()));
		} 
		if (codigo > 0){
			codigo = codigo + 1;
			getCotizacionModel().setDescripcion("000"+ String.valueOf(codigo) +"-" + fecha.obtenerFechaAnio(new Date()));
		} 
		
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

			
			setDatosServiciosModelGrid(datosServiciosModelGrid);
			listarCITE();

			pagina = "/paginas/ModuloProduccion/empresa/cotizacion/nuevo/nuevaCotizacion.xhtml";
			break;

		}
		return pagina;
	}
	
	
	public String enviarCotizacion() throws Exception {
		
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		LoginModel login = (LoginModel)
		facesContext.getExternalContext().getSessionMap().get("user");
		
		UsuarioBO usurio = new UsuarioBO();
		
		usurio = comunServices.buscarUsuario(login.getUsuario());
		String idUsuario="";
		idUsuario = usurio.getIdUsuario();
		ObtenerNumeroAleatorio numero = new ObtenerNumeroAleatorio();
		FormateadorFecha fecha = new FormateadorFecha();
		
		//inicializarClases();
		int sede =0;
		String ubigeo = "";
		String citeID = "1";
		String descripcionCITE = getServicioModel().getDescripcionCITE();
		
		if(descripcionCITE.equals("Cite Madera") ){
			citeID = "1";
		}
		if(descripcionCITE.equals("Cite Calzado") ){
			citeID = "2";
		}
		if(descripcionCITE.equals("Cite Agroindustrial") ){
			citeID = "3";
		}
		if(descripcionCITE.equals("Cite Pesquero") ){
			citeID = "4";
		}
		
		System.out.println("cite descripcion " + descripcionCITE);
		
		//esto luego en la tabla cite se debe cambiar en vez de 1 es 10 , 2 es 20 , 3 es 30 , 4 es 40
		int codigoCite = Integer.parseInt(citeID);
		codigoCite = codigoCite*10;
		List<ServicioBO> listaServicio = new ArrayList<ServicioBO>();

		List<CotizacionModel> datosServiciosModelGrid = new ArrayList<CotizacionModel>();
		
		int codigo = cotizacionServices.obtenerCodigoCotizacion();
		
		
		if(citeID.equals("1")){
			ubigeo = "140141";
			
		}
		if(citeID.equals("2")){
			ubigeo = "140122";
			
		}
		if(citeID.equals("3")){
			ubigeo = "100106";
			
		}
		if(citeID.equals("4")){
			ubigeo = "240106";
			
		}
		
		sede = cotizacionServices.obtenerCiteSede(ubigeo, descripcionCITE);
		System.out.println("Sede "+ sede);
		
		if (codigo == 0){
			codigo = 1;
			getCotizacionModel().setCodigo(codigo);
			
		} 
		if (codigo > 0){
			codigo = codigo + 1;
			getCotizacionModel().setCodigo(codigo);
			
		} 
		
		//SE CREA VARAIBLE PARA COLOCARLOS AL ATRIBUTO SECUENCIAL
		int codigoSecuencial = codigo;
		System.out.println("Tamanio de la lista de cotizacion " + getServicioCotizacion().size());
		for (ServicioModel servicio : getServicioCotizacion()) {
				CotizacionBO cotizacionBO = new CotizacionBO();
				
				
				cotizacionBO.setCodigo(codigo);	
				cotizacionBO.setSecuencial(codigoSecuencial + codigoCite + fecha.obtenerFechaAnio(new Date()) + numero.obtenerNumeroAleatorioEntero());
				cotizacionBO.setServicio(new ServicioBO());
				cotizacionBO.getServicio().setCodigo(servicio.getCodigo());
				cotizacionBO.setIdCite(codigoCite*10);
				
				cotizacionBO.setUsuario(new UsuarioBO());
				cotizacionBO.getUsuario().setIdUsuario(idUsuario);
				
				//Registrada igual 1 , aprobada igual a 2 y archivada igual a 3
				cotizacionBO.setEstado(1);
				cotizacionBO.setSede(sede);
				codigo ++;
				try {
					cotizacionServices.guardarCotizacion(cotizacionBO);
				}catch(Exception e){
					e.printStackTrace();
					mostrarMensaje(9);				
				}	
		}

			limpiarObjetos();
			mostrarMensaje(8);
			pagina = "/paginas/ModuloProduccion/cliente/cotizacion/nuevo/nuevaCotizacion.xhtml";
			
			return pagina ;
			
	}
	
	public String enviarCotizacionEmpresa() throws Exception {
		
		String pagina = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		LoginModel login = (LoginModel)
		facesContext.getExternalContext().getSessionMap().get("user");
		
		UsuarioBO usurio = new UsuarioBO();
		
		usurio = comunServices.buscarUsuario(login.getUsuario());
		String idUsuario="";
		idUsuario = usurio.getIdUsuario();
		ObtenerNumeroAleatorio numero = new ObtenerNumeroAleatorio();
		FormateadorFecha fecha = new FormateadorFecha();
		
		//inicializarClases();
		int sede =0;
		String ubigeo = "";
		String citeID = "1";
		String descripcionCITE = getServicioModel().getDescripcionCITE();
		
		if(descripcionCITE.equals("Cite Madera") ){
			citeID = "1";
		}
		if(descripcionCITE.equals("Cite Calzado") ){
			citeID = "2";
		}
		if(descripcionCITE.equals("Cite Agroindustrial") ){
			citeID = "3";
		}
		if(descripcionCITE.equals("Cite Pesquero") ){
			citeID = "4";
		}
		
		System.out.println("cite descripcion " + descripcionCITE);
		
		//esto luego en la tabla cite se debe cambiar en vez de 1 es 10 , 2 es 20 , 3 es 30 , 4 es 40
		int codigoCite = Integer.parseInt(citeID);
		codigoCite = codigoCite*10;
		List<ServicioBO> listaServicio = new ArrayList<ServicioBO>();

		List<CotizacionModel> datosServiciosModelGrid = new ArrayList<CotizacionModel>();
		
		int codigo = cotizacionServices.obtenerCodigoCotizacion();
		
		
		if(citeID.equals("1")){
			ubigeo = "140141";
			
		}
		if(citeID.equals("2")){
			ubigeo = "140122";
			
		}
		if(citeID.equals("3")){
			ubigeo = "100106";
			
		}
		if(citeID.equals("4")){
			ubigeo = "240106";
			
		}
		
		sede = cotizacionServices.obtenerCiteSede(ubigeo, descripcionCITE);
		System.out.println("Sede "+ sede);
		
		if (codigo == 0){
			codigo = 1;
			getCotizacionModel().setCodigo(codigo);
			
		} 
		if (codigo > 0){
			codigo = codigo + 1;
			getCotizacionModel().setCodigo(codigo);
			
		} 
		
		//SE CREA VARAIBLE PARA COLOCARLOS AL ATRIBUTO SECUENCIAL
		int codigoSecuencial = codigo;
		System.out.println("Tamanio de la lista de cotizacion " + getServicioCotizacion().size());
		for (ServicioModel servicio : getServicioCotizacion()) {
				CotizacionBO cotizacionBO = new CotizacionBO();
				
				
				cotizacionBO.setCodigo(codigo);	
				cotizacionBO.setSecuencial(codigoSecuencial + codigoCite + fecha.obtenerFechaAnio(new Date()) + numero.obtenerNumeroAleatorioEntero());
				cotizacionBO.setServicio(new ServicioBO());
				cotizacionBO.getServicio().setCodigo(servicio.getCodigo());
				cotizacionBO.setIdCite(codigoCite*10);
				
				cotizacionBO.setUsuario(new UsuarioBO());
				cotizacionBO.getUsuario().setIdUsuario(idUsuario);
				
				//Registrada igual 1 , aprobada igual a 2 y archivada igual a 3
				cotizacionBO.setEstado(1);
				cotizacionBO.setSede(sede);
				codigo ++;
				try {
					cotizacionServices.guardarCotizacion(cotizacionBO);
				}catch(Exception e){
					e.printStackTrace();
					mostrarMensaje(9);				
				}	
		}

			limpiarObjetos();
			mostrarMensaje(8);
			pagina = "/paginas/ModuloProduccion/empresa/cotizacion/nuevo/nuevaCotizacion.xhtml";
			
			return pagina ;
			
	}

	public void buscarServicio() throws Exception {

		
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
	
	public String verCotizacionEmpresa() throws Exception {
		String pagina = "";
		if(servicioCotizacion != null  ) {
			setServicioCotizacion(servicioCotizacion);
		}
		else setServicioCotizacion(selectedServicios);
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		LoginModel login = (LoginModel)
		facesContext.getExternalContext().getSessionMap().get("user");
		
		
		UsuarioBO usurio = new UsuarioBO();
		usurio = comunServices.buscarUsuario(login.getUsuario());
		
		getCotizacionModel().setUsuarioModel(new UsuarioModel());
		getCotizacionModel().getUsuarioModel().setNombres(usurio.getNombres());
		getCotizacionModel().getUsuarioModel().setDni(usurio.getDni());
		getCotizacionModel().getUsuarioModel().setDireccion(usurio.getDireccion());
		
		//datos de empresa
		getCotizacionModel().getUsuarioModel().setEmpresaModel(new EmpresaModel());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setRazonSocial(usurio.getEmpresa().getRazonSocial());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setRuc(usurio.getEmpresa().getRuc());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setRepresentante(usurio.getEmpresa().getRepresentante());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setNombreContacto(usurio.getEmpresa().getNombreContacto());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setNombreCargo(usurio.getEmpresa().getNombreCargo());
	
		
		String citeID = getServicioModel().getCodigoCITE();
		
		//SE ASOCIA A LAS CITES LA SEDE Y SU DESCRIPCION 
		if(citeID.equals("1")) 
		{	
			getServicioModel().setDescripcionCITE("Cite Madera");
			getServicioModel().setSede("LIMA");
		}
		
		if(citeID.equals("2")) 
		{	
			getServicioModel().setDescripcionCITE("Cite Calzado");
			getServicioModel().setSede("LIMA");
		}
		if(citeID.equals("3")) 
		{	
			getServicioModel().setDescripcionCITE("Cite Agroindustrial");
			getServicioModel().setSede("ICA");
		}
		if(citeID.equals("4")) 
		{	
			getServicioModel().setDescripcionCITE("Cite Pesquero");
			getServicioModel().setSede("CALLAO");
		}
		
		
		pagina = "/paginas/ModuloProduccion/empresa/cotizacion/nuevo/verCotizacion.xhtml";

		return pagina;
	}
	public String verCotizacion() throws Exception {
		String pagina = "";
		if(servicioCotizacion != null  ) {
			setServicioCotizacion(servicioCotizacion);
		}
		else setServicioCotizacion(selectedServicios);
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		LoginModel login = (LoginModel)
		facesContext.getExternalContext().getSessionMap().get("user");
		
		
		UsuarioBO usurio = new UsuarioBO();
		usurio = comunServices.buscarUsuario(login.getUsuario());
		
		getCotizacionModel().setUsuarioModel(new UsuarioModel());
		getCotizacionModel().getUsuarioModel().setNombres(usurio.getNombres());
		getCotizacionModel().getUsuarioModel().setDni(usurio.getDni());
		getCotizacionModel().getUsuarioModel().setDireccion(usurio.getDireccion());
		
		//datos de empresa
		getCotizacionModel().getUsuarioModel().setEmpresaModel(new EmpresaModel());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setRazonSocial(usurio.getEmpresa().getRazonSocial());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setRuc(usurio.getEmpresa().getRuc());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setRepresentante(usurio.getEmpresa().getRepresentante());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setNombreContacto(usurio.getEmpresa().getNombreContacto());
		getCotizacionModel().getUsuarioModel().getEmpresaModel().setNombreCargo(usurio.getEmpresa().getNombreCargo());
	
		
		String citeID = getServicioModel().getCodigoCITE();
		
		//SE ASOCIA A LAS CITES LA SEDE Y SU DESCRIPCION 
		if(citeID.equals("1")) 
		{	
			getServicioModel().setDescripcionCITE("Cite Madera");
			getServicioModel().setSede("LIMA");
		}
		
		if(citeID.equals("2")) 
		{	
			getServicioModel().setDescripcionCITE("Cite Calzado");
			getServicioModel().setSede("LIMA");
		}
		if(citeID.equals("3")) 
		{	
			getServicioModel().setDescripcionCITE("Cite Agroindustrial");
			getServicioModel().setSede("ICA");
		}
		if(citeID.equals("4")) 
		{	
			getServicioModel().setDescripcionCITE("Cite Pesquero");
			getServicioModel().setSede("CALLAO");
		}
		
		
		pagina = "/paginas/ModuloProduccion/cliente/cotizacion/nuevo/verCotizacion.xhtml";

		return pagina;
	}

	public String selectorBuscarCotizacionServicio(int modo) throws Exception {
		String pagina = "";

		switch (modo) {
		case 1:
			
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
		setCotizacionModel(new CotizacionModel());
		//setServicioCotizacion(new ArrayList<ServicioModel>());
			
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
					"La cotizacion se guardo correctamente");
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
		setServicioCotizacion(selectedServicios);
		
	}
	
	public void setSelectedServicios(List<ServicioModel> selectedServicios) {
		this.totalSuma = 0.0;
		for (int i = 0; i < selectedServicios.size(); i++) {
			this.totalSuma = this.totalSuma + DoubleDosDecimales(Double.parseDouble(selectedServicios.get(i).getPrecioDeVenta()));
		}

		this.totalSuma = DoubleDosDecimales(this.totalSuma);
		this.selectedServicios = selectedServicios;
	}
	
	

	public List<ServicioModel> getServicioCotizacion() {
		return servicioCotizacion;
	}

	public void setServicioCotizacion(List<ServicioModel> servicioCotizacion) {
		this.totalSumaCotizar = 0.0;
		for (int i = 0; i < servicioCotizacion.size(); i++) {
			this.totalSumaCotizar = this.totalSumaCotizar + DoubleDosDecimales(Double.parseDouble(servicioCotizacion.get(i).getPrecioDeVenta()));
		}

		this.totalSumaCotizar = DoubleDosDecimales(this.totalSumaCotizar);
		this.servicioCotizacion = servicioCotizacion;
	}

	public List<ServicioModel> getDatosServiciosModelGrid() {
		return datosServiciosModelGrid;
	}

	public void setDatosServiciosModelGrid(
			List<ServicioModel> datosServiciosModelGrid) {
		this.datosServiciosModelGrid = datosServiciosModelGrid;
	}
	
	public Double getTotalSuma() {
		return totalSuma;
	}

	public void setTotalSuma(Double totalSuma) {
		this.totalSuma = totalSuma;
	}

	public Double getTotalSumaCotizar() {
		return totalSumaCotizar;
	}

	public void setTotalSumaCotizar(Double totalSumaCotizar) {
		this.totalSumaCotizar = totalSumaCotizar;
	}
	
	
}
