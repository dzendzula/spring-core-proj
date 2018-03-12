package net.servodata.app.system.exception;

import lombok.Getter;

/**
 * @author <a href="mailto:stepan.marek@doxologic.com">Stepan Marek</a>
 */
public enum ParamSource {

    PATH("path"),
    PARAMETER("parameter");

    @Getter
    private final String key;

    ParamSource(String key) {
        this.key = key;
    }
}
