package pe.gob.produce.produccion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.produce.produccion.bo.MuestraBO;
import pe.gob.produce.produccion.bo.UbigeoBO;
import pe.gob.produce.produccion.bo.UsuarioBO;
import pe.gob.produce.produccion.dao.ComunIDAO;
import pe.gob.produce.produccion.services.ComunServices;

@Component("comunServices")
public class ComunServicesImpl implements ComunServices{

	@Autowired
	private ComunIDAO comunDAO;
	

	@Override
	public List<UbigeoBO> listUbigeo() throws Exception {
		// TODO Auto-generated method stub
		return comunDAO.listUbigeo();
	}


	@Override
	public List<UbigeoBO> listarProvincia(String codDepartamento) throws Exception {
		// TODO Auto-generated method stub
		return comunDAO.listarProvincia(codDepartamento);
	}


	@Override
	public List<UbigeoBO> listarDistrito(String codDepartamento, String codProvincia) throws Exception {
		// TODO Auto-generated method stub
		return comunDAO.listarDistrito(codDepartamento,codProvincia);
	}


	@Override
	public List<MuestraBO> listarMuestra() throws Exception {
		// TODO Auto-generated method stub
		return comunDAO.listarMuestra();
	}
	
	@Override
	public UsuarioBO buscarUsuario(String codUsuario) throws Exception {
		// TODO Auto-generated method stub
		return comunDAO.buscarUsuario(codUsuario);
	}
	
	

}
