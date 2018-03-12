package net.servodata.app.domain.commons.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.servodata.app.domain.commons.bo.IdentifiedBo;
import net.servodata.app.domain.commons.dao.SdJpaRepository;

/**
 * @author <a href="mailto:zhezhela@servodata.net">Oleksandr Zhezhela</a>
 */
@Service
public interface AbstractService<B extends IdentifiedBo, R extends SdJpaRepository<B, Long>> {

    default B findById(Long id) {
        if (id == null || id.longValue() <= 0L) {
            return null;
        }
        return getRepository().findById(id).orElse(null);
    }

    default B findByIdNullSafe(Long id) throws NullPointerException {
        if (id == null || id.longValue() <= 0L) {
            throw new IllegalArgumentException();
        }
        return getRepository().findById(id).orElseThrow(() -> new NotFoundException(getManagedClass(), id));
    }

    default List<B> findByIds(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            return new ArrayList<>();
        }
        return getRepository().findAllById(ids);
    }

    R getRepository();

    Class<B> getManagedClass();
}
