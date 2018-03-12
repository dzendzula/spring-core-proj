package net.servodata.app.system.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;


/**
 * @author <a href="mailto:spilar@servodata.net">Martin Spilar</a>
 */
public class ConflictValidationException extends AbstractException {

    // --- fields ---

    private final MessageSourceResolvable error;

    // --- getters/setters ---

    public MessageSourceResolvable getError() {
        return error;
    }

    // --- constructor ---

    @Deprecated
    public ConflictValidationException(String message) {
        this(createError(message));
    }

    public ConflictValidationException(MessageSourceResolvable error) {
        super(error.getDefaultMessage());
        this.error = error;
    }

    // --- methods ---

    private static MessageSourceResolvable createError(String message) {
        return new DefaultMessageSourceResolvable(null, message);
    }

    public static ConflictValidationException ofMessageCode(String messageCode) {
        return new ConflictValidationException(new DefaultMessageSourceResolvable(messageCode));
    }

    public static ConflictValidationException ofMessageCode(String messageCode, Object... arguments) {
        return new ConflictValidationException(new DefaultMessageSourceResolvable(new String[]{messageCode}, arguments));
    }

    public static ConflictValidationException ofMessageCode(String messageCode, String defaultMessage, Object... arguments) {
        return new ConflictValidationException(new DefaultMessageSourceResolvable(new String[]{messageCode}, arguments, defaultMessage));
    }
}
