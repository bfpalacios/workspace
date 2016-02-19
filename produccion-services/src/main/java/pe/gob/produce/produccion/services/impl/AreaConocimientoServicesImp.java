package pe.gob.produce.produccion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.gob.produce.produccion.dao.AreaConocimientoDao;
import pe.gob.produce.produccion.services.AreaConocimientoServices;

/**
 * Created by Alex on 06/11/2015
 */
@Service("areaConocimientoServices")
public class AreaConocimientoServicesImp implements AreaConocimientoServices {

    @Autowired
    private AreaConocimientoDao areaConocimientoDao;

    public List<AreaConocimientoBO> listarTodos() throws Exception {
        return areaConocimientoDao.listarTodos();
    }

    @Override
    public List<AreaConocimientoBO> listarAreasAprobadasTutor(String codigoTutor) throws Exception {
        return  areaConocimientoDao.listarAreasAprobadasTutor(codigoTutor);
    }

    @Override
    public AreaConocimientoBO obtnerAreaTema(int codigoTema) {
        return areaConocimientoDao.obtnerAreaTema(codigoTema);
    }
}
