package pe.gob.produce.produccion.dao;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

/**
 * Created by Alex on 06/11/2015
 */
public interface AreaConocimientoDao {
    public List<AreaConocimientoBO> listarTodos() throws Exception;
    public List<AreaConocimientoBO> listarAreasAprobadasTutor(String codigoTutor) throws Exception;

    AreaConocimientoBO obtnerAreaTema(int codigoTema);
}
