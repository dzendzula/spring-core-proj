package net.servodata.app.domain.commons.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="mailto:spilar@servodata.net">Martin Spilar</a>
 */
@Getter
@Setter
public abstract class AbstractExtendedRegister implements Serializable {

    private Long id;
    private boolean active;
    private String name;
    private String code;
    private int position;
}
