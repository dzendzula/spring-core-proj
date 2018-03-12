package net.servodata.app.domain.commons.dao;

import java.util.List;

import net.servodata.app.domain.commons.bo.IdentifiedBo;

/**
 * @author <a href="mailto:stepan.marek@doxologic.com">Stepan Marek</a>
 */
public interface GenericRepository {
    List<Long> findAllId(Class<? extends IdentifiedBo> entityClass);
}
