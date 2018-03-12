package net.servodata.app.domain.commons.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import net.servodata.app.domain.commons.bo.IdentifiedBo;


/**
 * @author <a href="mailto:stepan.marek@doxologic.com">Stepan Marek</a>
 */
@NoRepositoryBean
public interface SdJpaRepository<T extends IdentifiedBo, ID extends Long> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {
}
