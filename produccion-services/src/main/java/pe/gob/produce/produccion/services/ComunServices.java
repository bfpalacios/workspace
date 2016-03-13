package pe.gob.produce.produccion.services;

import java.util.List;

import pe.gob.produce.produccion.bo.ServicioBO;

public interface ComunServices {
	List<ServicioBO> listarTablaMaestra(String tabla, String campo) throws Exception;
}
