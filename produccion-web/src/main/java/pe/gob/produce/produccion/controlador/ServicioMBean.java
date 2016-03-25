package pe.gob.produce.produccion.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.gob.produce.produccion.bo.ServicioBO;
import pe.gob.produce.produccion.core.util.Convertidor;
import pe.gob.produce.produccion.core.util.FormateadorFecha;
import pe.gob.produce.produccion.model.LoginModel;
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

		 listarServicios();
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
		
		//inicializarClases();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		LoginModel login = (LoginModel) facesContext.getExternalContext().getSessionMap().get("user");
		System.out.println("login user " + login.getUsuario());
		
		String nombreServicio = getServicioModel().getNombre()==null?"":getServicioModel().getNombre();
		String codigoServicio = getServicioModel().getCodigo()==null?"":getServicioModel().getCodigo();
		String codigoCITE = getServicioModel().getCodigoCITE()==null?"":getServicioModel().getCodigoCITE();
		
		System.out.println("dATOS SERVICIO BUSQUEDA " + nombreServicio + "-" + codigoServicio + "-" + codigoCITE);
		
		List<ServicioBO> listaServicio = new ArrayList<ServicioBO>();
		//SE ENVIA EL 6 POR DEFAULT
		listaServicio = servicioServices.buscarServicio(codigoServicio,nombreServicio, 6);
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
					
					listaServicio = servicioServices.buscarServicio("", "", 6);
					
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
				 	
					listaServicio = servicioServices.buscarServicio("", "", 6);
					
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
				 	listaServicio = servicioServices.buscarServicio("", "", 6);
					
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
		try{
			//if (buscarUsuario(getUsuarioModel().getIdUsuario()==null?"0":getUsuarioModel().getIdUsuario()).equals("")){
			if (true){
				String nuevoServicio = getServicioModel().getNombre()==null?"":getServicioModel().getNombre();
				String citeID = getServicioModel().getCodigoCITE()==null?"":getServicioModel().getCodigoCITE();
				String fecha = (String) (getDate()==null?"":getDate());
				String descripcion = getServicioModel().getDescripcion()==null?"":getServicioModel().getDescripcion();
				String nombreSolicitante = getServicioModel().getNombreSolicitante()==null?"":getServicioModel().getNombreSolicitante();
				String cargo = getServicioModel().getCargo()==null?"":getServicioModel().getCargo();
				String telefonos = getServicioModel().getTelefono()==null?"":getServicioModel().getTelefono();
				String email = getServicioModel().getTelefono()==null?"":getServicioModel().getTelefono();
				
			servicioServices.nuevoServicio(nuevoServicio,citeID, fecha, descripcion, nombreSolicitante, cargo, telefonos, email );
			}
		}
		catch(Exception e){
			e.printStackTrace();
			//mostrarMensaje(9);				
		}		
		limpiarObjetos();
		//llenarRolesObservados();
		
		/*switch(PROCESO){
			case 1: switch(MODO_USUARIO){
						case 1: pagina = "/paginas/ModuloObservados/admin/mantenimiento/usuario/nuevoUsuarioMO.xhtml"; break;
						case 2: pagina = "/paginas/ModuloObservados/ocaa/mantenimiento/usuario/nuevoUsuarioMO.xhtml"; break;
					}
			
			case 2: switch(MODO_USUARIO){
						case 1: pagina = "/paginas/ModuloRegulares/admin/mantenimiento/usuario/nuevoUsuarioMR.xhtml"; break;
						case 2: pagina = "/paginas/ModuloRegulares/ocaa/mantenimiento/usuario/nuevoUsuarioMR.xhtml"; break;
					}				
		}*/	
		
		
		//
		try {
			selectorNuevoServicio(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void limpiarObjetos(){
		setServicioModel(null);
		setServicioModel(new ServicioModel());
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
	
	public void setServicioModel(ServicioModel servicioModel) {
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
