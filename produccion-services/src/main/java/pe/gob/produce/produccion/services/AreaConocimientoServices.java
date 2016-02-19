package pe.gob.produce.produccion.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

/**
 * Created by Alex on 06/11/2015
 */
public interface AreaConocimientoServices {
    public List<AreaConocimientoBO> listarTodos() throws Exception;
    public List<AreaConocimientoBO> listarAreasAprobadasTutor(String codigoTutor) throws Exception;
    AreaConocimientoBO obtnerAreaTema(int codigoTema);
}
