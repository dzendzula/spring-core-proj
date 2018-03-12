package net.servodata.app.domain.commons.bo;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractBo implements IdentifiedBo, Serializable {
}
