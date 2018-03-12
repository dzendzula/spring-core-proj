/*
 * $Id$
 */
package net.servodata.app.domain.commons.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEventListener;

/**
 * @author <a href="mailto:stepan.marek@doxologic.com">Stepan Marek</a>
 * @version $Revision$ $Date$
 */
public abstract class AbstractHibernateListener implements PreInsertEventListener, PreUpdateEventListener, PreDeleteEventListener {

    @Resource
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void registerListeners() {

        EventListenerRegistry registry = (((SessionFactoryImplementor) entityManagerFactory)).getServiceRegistry().getService(EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(this);
        registry.getEventListenerGroup(EventType.PRE_DELETE).appendListener(this);
    }

}
