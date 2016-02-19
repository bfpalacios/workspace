package pe.gob.produce.produccion.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;

public interface GrupoServices {
	
	public void  guardarGrupos(List<GrupoBO> lista) throws Exception;

}
