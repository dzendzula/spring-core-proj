package net.servodata.app.domain.commons.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import net.servodata.app.domain.commons.bo.AbstractTypeBo;


/**
 * @author <a href="mailto:stepan.marek@doxologic.com">Stepan Marek</a>
 */
@NoRepositoryBean
public interface AbtractTypeSdJpaRepository<T extends AbstractTypeBo, ID extends Long> extends SdJpaRepository<T, ID>, QuerydslPredicateExecutor<T> {

    List<T> findByActiveTrue(Sort sort);

}
