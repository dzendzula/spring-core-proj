package net.servodata.app.domain.commons.dao.impl;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import net.servodata.app.domain.commons.bo.IdentifiedBo;


/**
 * @author <a href="mailto:zhezhela@servodata.net">Oleksandr Zhezhela</a>
 */
//@Repository
//@Transactional(readOnly = true)
public class SimpleSdJpaRepository<T extends IdentifiedBo, ID extends Long> {//extends SimpleJpaRepository<T, ID> implements SdJpaRepository<T, ID> {

    private Class<T> clazz;

    public SimpleSdJpaRepository() {

    }

    public SimpleSdJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        //        super(entityInformation, entityManager);

    }

    public SimpleSdJpaRepository(Class<T> domainClass, EntityManager em) {
        //        super(domainClass, em);
    }


    //    @Override
    public T safeFindById(ID id) {
        if (id == null || id.longValue() < 0L) {
            return null;
        }
        return null;//findById(id).orElseThrow(() -> new NotFoundException(clazz, id));
    }


}
