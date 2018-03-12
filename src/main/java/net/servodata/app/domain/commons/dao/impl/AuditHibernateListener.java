package net.servodata.app.domain.commons.dao.impl;

import java.time.OffsetDateTime;

import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreUpdateEvent;



/**
 * @author <a href="mailto:stepan.marek@doxologic.com">Stepan Marek</a>
 */
public class AuditHibernateListener extends AbstractHibernateListener {

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        Object entity = event.getEntity();
        if (entity instanceof AuditableBo) {
            AuditableBo bo = (AuditableBo) entity;
            String user = "unknown";
            bo.setCreateBy(user);
            bo.setLastModifiedBy(user);
            OffsetDateTime sysdate = OffsetDateTime.now();
            bo.setCreatedDate(sysdate);
            bo.setLastModifiedDate(sysdate);
        }
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        Object entity = event.getEntity();
        if (entity instanceof AuditableBo) {
            AuditableBo bo = (AuditableBo) entity;
            bo.setLastModifiedBy("unknown");
            bo.setLastModifiedDate(OffsetDateTime.now());
        }
        return false;
    }

    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
        return false;
    }

}
