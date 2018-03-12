package net.servodata.app.domain.commons.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;




/**
 * @author <a href="mailto:stepan.marek@doxologic.com">Stepan Marek</a>
 */
@Repository
public class GenericRepositoryImpl implements GenericRepository {

    // --- fields ---

    @PersistenceContext(unitName = "appPersistenceUnit")
    private EntityManager entityManager;

    // --- methods ---

    @Override
    public List<Long> findAllId(Class<? extends IdentifiedBo> entityClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<? extends IdentifiedBo> bo = query.from(entityClass);
        query.select(bo.get("id"));
        return entityManager.createQuery(query).getResultList();
    }

}