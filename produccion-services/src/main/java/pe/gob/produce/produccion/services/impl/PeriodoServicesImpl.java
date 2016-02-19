package pe.gob.produce.produccion.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;
import pe.gob.produce.produccion.dao.jdbc.PeriodoDAO;
import pe.gob.produce.produccion.services.PeriodoServices;

@Service("periodoServices")
public class PeriodoServicesImpl implements PeriodoServices {

	@Autowired
	private PeriodoDAO periodoDao;

	public PeriodoServicesImpl() {

	}

	@Override
	public List<PeriodoBO> getPeriodos() {
		return this.periodoDao.getPeriodos();
	}

	public PeriodoBO getPeriodo(Integer id) {
		return this.periodoDao.getPeriodo(id);
	}

	@Override
	public PeriodoBO getPeriodoActual() {
		return this.periodoDao.getPeriodoActual();
	}

}
