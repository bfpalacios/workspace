package pe.gob.produce.produccion.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.gob.produce.produccion.bo.CITEBO;
import pe.gob.produce.produccion.bo.ServicioBO;
import pe.gob.produce.produccion.bo.UbigeoBO;
import pe.gob.produce.produccion.core.util.Convertidor;
import pe.gob.produce.produccion.core.util.FormateadorFecha;
import pe.gob.produce.produccion.core.util.ObtenerNumeroAleatorio;
import pe.gob.produce.produccion.model.ServicioModel;
import pe.gob.produce.produccion.services.CITEServices;
import pe.gob.produce.produccion.services.ComunServices;
import pe.gob.produce.produccion.services.ServicioServices;



@Controller("servicioMBean")
@ViewScoped
public class ServicioMBean {
	
	@Autowired
	private ServicioModel servicioModel;
	
	@Autowired
	private CITEServices citeServices;
	
	
	@Autowired
	private ComunServices comunServices;
	
	@Autowired
	private ServicioServices servicioServices;
	
	//datos complementarios de la pantalla
	private Date date;
	
	//constantes
	private int MODO_USUARIO;
	private static int MODO_ADMIN = 1;
	private static int MODO_EMPLEADO = 2;
	
	
	//para la lista de servicios se declara una variable list de tipo ServicioModel
	private List<ServicioModel>  datosServiciosModelGrid;
	private List<ServicioModel>  selectedServicios;
	
	
	
	//constructor
	public ServicioMBean(){
		System.out.println("::::: LOADING ServicioMBean ::::::::");		
		inicializarClases();		
		new Convertidor();
		new FormateadorFecha();
		date=new Date();
		
		
	}
	

	
	
	private void inicializarClases(){
		this.servicioModel = new ServicioModel();		
		
	}
	
	
	public String selectorNuevoServicio(int modo) throws Exception{
		 String pagina = "";
			
		 inicializarClases();

		 listarCITE();

		 //listarServicios();
		 switch(modo){ 
		 case 1: 	
			
			pagina = "/paginas/ModuloProduccion/cliente/servicio/nuevo/nuevoServicio.xhtml"; break;
		
		 /*@@ESTE ES EL CASO PARA PERFIL CITE */
		 case 2:  						
			 
			listarMuestras(); 
			pagina = "/paginas/ModuloProduccion/cite/servicio/nuevo/nuevoServicio.xhtml"; break;
		 
		/*@@ESTE ES EL CASO PARA PERFIL EMPRESA */
		 case 3:  									
			
		 	pagina = "/paginas/ModuloProduccion/empresa/servicio/nuevo/nuevoServicio.xhtml"; break;
	 
		}
		return pagina;		
	}
	
	public void buscarServicio() throws Exception{
		
		/*FacesContext facesContext = FacesContext.getCurrentInstance();
		LoginModel login = (LoginModel) facesContext.getExternalContext().getSessionMap().get("user");
		System.out.println("login user " + login.getUsuario() + "hola" + getServicioModel().getCodigoCITE() + "que tal");
		*/
		String nombreServicio = getServicioModel().getNombre()==""?null:getServicioModel().getNombre();
		String codigoServicio = getServicioModel().getCodigo()==""?null:getServicioModel().getCodigo();
		String codigoCITE = getServicioModel().getCodigoCITE()==""?"0":getServicioModel().getCodigoCITE();
		
		if (getServicioModel().getCodigoCITE() == null){
			codigoCITE= "0";
			
		}
		
		
		
		System.out.println("dATOS SERVICIO BUSQUEDA " + nombreServicio + "-" + codigoServicio + "-" + codigoCITE);
		
		List<ServicioBO> listaServicio = new ArrayList<ServicioBO>();
		//SE ENVIA EL 6 POR DEFAULT
		listaServicio = servicioServices.buscarServicio(codigoServicio,nombreServicio, Integer.parseInt(codigoCITE));
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
	
	
	public String selectorBuscarServicio(int modo) throws Exception{
		String pagina = "";
		inicializarClases();
		List<ServicioBO> listaServicio = new ArrayList<ServicioBO>();
			
		List<ServicioModel> datosServiciosModelGrid = new ArrayList<ServicioModel>();
			
		switch(modo){ 
			case 1:  								
					//SE ENVIA 0 EN EL CODIGO DE CITE PARA QUE NOS OBTENGA TODOS LOS SERVICIOS DE LOS CITES
					listaServicio = servicioServices.buscarServicio(null, null, 0);
					
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
					pagina = "/paginas/ModuloProduccion/cliente/servicio/buscar/buscarServicio.xhtml"; break;
			/*@@ESTE ES EL CASO PARA PERFIL CITE */
			case 2:  
				 	//SE ENVIA 0 EN EL CODIGO DE CITE PARA QUE NOS OBTENGA TODOS LOS SERVICIOS DE LOS CITES
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
					pagina = "/paginas/ModuloProduccion/cite/servicio/buscar/buscarServicio.xhtml"; break;
					
			/*@@ESTE ES EL CASO PARA PERFIL EMPRESA */
			case 3:  
					//SE ENVIA 0 EN EL CODIGO DE CITE PARA QUE NOS OBTENGA TODOS LOS SERVICIOS DE LOS CITES
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
					pagina = "/paginas/ModuloProduccion/empresa/servicio/buscar/buscarServicio.xhtml"; break;
					
			
		}
		return pagina;		
	 }
	
	
	


	public void guardarNuevoServicio(int opcion) {
		String pagina = "";
		ObtenerNumeroAleatorio numero = new ObtenerNumeroAleatorio();
		FormateadorFecha fecha = new FormateadorFecha();
		try{
			//if (buscarUsuario(getUsuarioModel().getIdUsuario()==null?"0":getUsuarioModel().getIdUsuario()).equals("")){
			if (true){
				String nombreServicio = getServicioModel().getNombre()==null?"":getServicioModel().getNombre();
				String citeID = getServicioModel().getCodigoCITE()==null?"":getServicioModel().getCodigoCITE();
				String unidad = getServicioModel().getUnidad()==null?"":getServicioModel().getUnidad();
				String requisito = getServicioModel().getRequisito()==null?"":getServicioModel().getRequisito();
				String valorDeVenta = getServicioModel().getValorDeVenta()==null?"":getServicioModel().getValorDeVenta();
				String precioDeVenta = getServicioModel().getPrecioDeVenta()==null?"":getServicioModel().getPrecioDeVenta();
				String codigoServicio ="";
				String codigoUbigeo = "1";
				
				if(citeID.equals("1")) 
				{
					codigoServicio = "0001" + fecha.formatoFechaDDMMAAAA2(new Date()) + String.valueOf(numero.obtenerNumeroAleatorioEntero());
				}
				
				if(citeID.equals("2")) 
				{
					codigoServicio = "0002" + fecha.formatoFechaDDMMAAAA2(new Date()) + String.valueOf(numero.obtenerNumeroAleatorioEntero());
				}
				if(citeID.equals("3")) 
				{
					codigoServicio = "0003" + fecha.formatoFechaDDMMAAAA2(new Date()) + String.valueOf(numero.obtenerNumeroAleatorioEntero());
				}
				if(citeID.equals("4")) 
				{
					codigoServicio = "0004" + fecha.formatoFechaDDMMAAAA2(new Date()) + String.valueOf(numero.obtenerNumeroAleatorioEntero());
				}
				
					
				ServicioBO servicio = new ServicioBO();
					servicio.setCodigo(codigoServicio);
					
					servicio.setUbigeo(new UbigeoBO());
					servicio.getUbigeo().setIdUbigeo(codigoUbigeo);
					servicio.setCite(new CITEBO());
					servicio.getCite().setCodigo(citeID);
					servicio.setEstado("1");
					servicio.setUnidad(unidad);
					servicio.setNombre(nombreServicio);
					servicio.setRequisito(requisito);
					servicio.setPrecioDeVenta(precioDeVenta);
					servicio.setValorDeVenta(valorDeVenta);
					
					
				servicioServices.nuevoServicio(servicio);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			mostrarMensaje(9);				
		}		
		limpiarObjetos();
		mostrarMensaje(8);	
		//llenarRolesObservados();
		
		switch(opcion){
			case 1: switch(MODO_USUARIO){
						case 1: pagina = "/paginas/ModuloObservados/admin/mantenimiento/usuario/nuevoUsuarioMO.xhtml"; break;
						case 2: pagina = "/paginas/ModuloObservados/ocaa/mantenimiento/usuario/nuevoUsuarioMO.xhtml"; break;
					}
			
			case 2: 
			try {
				selectorNuevoServicio(opcion);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} break;
						
									
		}	
		
	}
	
	
	private void limpiarObjetos(){
		setServicioModelbi(null);
		setServicioModelbi(new ServicioModel());
	}

	private void listarCITE(){
		try{
			
		
			getServicioModel().setListarCITE(citeServices.listarCITES());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void listarMuestras(){
		try{
			
		
			getServicioModel().setListarMuestra(comunServices.listarMuestra());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	private void listarServicios(){
		
		try{
				
			getServicioModel().setListarCITE(citeServices.listarCITES());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String cancelar() throws Exception{
		 String pagina = "";
		 
		 	inicializarClases();

			listarCITE();
			pagina = "/paginas/ModuloProduccion/cliente/servicio/nuevo/nuevoServicio.xhtml"; 
			
		return pagina;		
	}
	
	private void mostrarMensaje(int opcionMensaje){
		FacesMessage message = null;		
		
		switch(opcionMensaje){
			case 1: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe ingresar sólo caracteres en el campo - " + "Nombres");
	        		FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 2: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe ingresar sólo caracteres en el campo - " + "Apellido Paterno");
	        		FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 3: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe ingresar sólo caracteres en el campo - " + "Apellido Materno");
    				FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 4: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe ingresar un correo válido en el campo - " + "Correo");
    				FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 5: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe ingresar sólo números en el campo - " + "Teléfono");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 6: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe ingresar sólo números en el campo - " + "Código del alumno");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 7: message = new FacesMessage(FacesMessage.SEVERITY_WARN,"", "El usuario ingresado ya ha sido registrado");
					FacesContext.getCurrentInstance().addMessage(null, message); break;	
			case 8: message = new FacesMessage(FacesMessage.SEVERITY_INFO,"", "El servicio se guardo correctamente");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 9: message = new FacesMessage(FacesMessage.SEVERITY_FATAL,"", "Hubo un error al guardar el usuario");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
		}
	}
	
	public void setServicioModelbi(ServicioModel servicioModel) {
		this.servicioModel = servicioModel;
	}

	public ServicioModel getServicioModel() {
		return servicioModel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public List<ServicioModel> getSelectedServicios() {
		return selectedServicios;
	}




	public void setSelectedServicios(List<ServicioModel> selectedServicios) {
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
