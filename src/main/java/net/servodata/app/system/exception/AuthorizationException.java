package net.servodata.app.system.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.AccessDeniedException;

/**
 * @author <a href="mailto:senk@servodata.net">Zdenek Senk</a>
 * @version $Revision$ $Date$
 */
public class AuthorizationException extends AccessDeniedException {

    public static final DefaultMessageSourceResolvable DEFAULT_MESSAGE = new DefaultMessageSourceResolvable(new String[]{"validation.security.access_denied"}, "Access denied");

    // --- fields ---

    private final MessageSourceResolvable error;

    // --- getters/setters ---

    public MessageSourceResolvable getError() {
        return error;
    }

    // --- constructor ---

    public AuthorizationException() {
        super("Access denied");
        this.error = DEFAULT_MESSAGE;
    }

    public AuthorizationException(String msg) {
        super(msg);
        this.error = new DefaultMessageSourceResolvable(new String[]{"validation.security.access_denied"}, msg);
    }

    public AuthorizationException(MessageSourceResolvable error) {
        super(error.getDefaultMessage());
        this.error = error;
    }

}
