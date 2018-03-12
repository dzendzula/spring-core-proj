package net.servodata.app.system.exception;

import org.springframework.context.MessageSourceResolvable;

/**
 * @author <a href="mailto:spilar@servodata.net">Martin Spilar</a>
 */
public abstract class AbstractException extends RuntimeException {

    public abstract MessageSourceResolvable getError();

    protected AbstractException(String message) {
        super(message);
    }

}
