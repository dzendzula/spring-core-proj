package net.servodata.app.domain.commons.bo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * User: m.svoboda
 * Date: 22.12.14
 * Time: 21:58
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractTypeBo extends AbstractBo {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    /**
     * je zaznam registru platny - aktualne platny k vyberum apod.
     */
    @Column(name = "active", nullable = false)
    private boolean active = true;

    /**
     * Poradi pro ucely zobrazovani
     */
    @Column(name = "position", nullable = false)
    private int position;

}
