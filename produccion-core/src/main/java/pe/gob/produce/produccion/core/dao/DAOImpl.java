package pe.gob.produce.produccion.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class DAOImpl<Entidad,Id extends Serializable> implements IDAO<Entidad, Id>{

	@Autowired
	protected SessionFactory sessionFactory;

	public void insertar(Entidad entidad) throws Exception {
		sessionFactory.getCurrentSession().persist(entidad);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().evict(entidad);
	}

	public void actualizar(Entidad entidad) throws Exception {
		sessionFactory.getCurrentSession().merge(entidad);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().evict(entidad);
	}

	public void eliminar(Serializable id) throws Exception {
		
	} 
	
	@SuppressWarnings("unchecked")
	public Entidad obtenerEntidadPorId(Class<Entidad> clase, Serializable id)
			throws Exception {
		return (Entidad) sessionFactory.getCurrentSession().createCriteria(clase).add(Restrictions.idEq(id)).uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Entidad> obtenerListEntidadPorParametro(Class<Entidad> clase, Map<String,Object> parametro)
			throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clase);
		
		for(Map.Entry<String, Object> row : parametro.entrySet()){
			criteria.add(Restrictions.eq(row.getKey(), row.getValue()));
		}
		return (List<Entidad>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Entidad> listarTodos(Class<Entidad> clase) throws Exception {
		List<Entidad> listaEntidad = new ArrayList<Entidad>();
	    sessionFactory.getCurrentSession().flush();
	    listaEntidad = sessionFactory.getCurrentSession().createCriteria(clase).list();
	    return listaEntidad;
	}
	
	
}
