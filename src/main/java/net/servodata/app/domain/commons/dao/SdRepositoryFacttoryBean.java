package net.servodata.app.domain.commons.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import net.servodata.app.domain.commons.bo.IdentifiedBo;
import net.servodata.app.domain.commons.dao.impl.SimpleSdJpaRepository;


/**
 * @author <a href="mailto:zhezhela@servodata.net">Oleksandr Zhezhela</a>
 */

public class SdRepositoryFacttoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends
        JpaRepositoryFactoryBean<R, T, ID> {

    public SdRepositoryFacttoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {

        return new SdRepositoryFactory(entityManager);
    }


    private static class SdRepositoryFactory<T extends IdentifiedBo, ID extends Long> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public SdRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        @Override
        protected Object getTargetRepository(RepositoryInformation metadata) {
            return new SimpleSdJpaRepository<>((Class<T>) metadata.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            // to check for QueryDslJpaRepository's which is out of scope.
            return SimpleSdJpaRepository.class;
        }
    }
}
