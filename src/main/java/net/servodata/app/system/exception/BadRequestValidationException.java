package net.servodata.app.system.exception;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;


/**
 * @author <a href="mailto:spilar@servodata.net">Martin Spilar</a>
 */
public class BadRequestValidationException extends AbstractException {

    // --- fields ---

    private final ObjectError error;

    // --- getters/setters ---

    public ObjectError getError() {
        return error;
    }

    // --- constructor ---

    /**
     * @deprecated todo[ER-5177]: odstranit konstruktor, az budou vsechny vyskyty vyresene pres ObjectError
     */
    @Deprecated
    public BadRequestValidationException(String message) {
        super(message);
        this.error = null;
    }

    public BadRequestValidationException(ObjectError error) {
        super(error.getDefaultMessage());
        this.error = error;
    }

    // --- methods ---

    // todo[ER-5177]: predelat factory metody na konstruktory

    public static BadRequestValidationException toFieldError(String message, ParamSource source, String parameter, Object rejectedValue) {
        return new BadRequestValidationException(new FieldError(source.getKey(), parameter, rejectedValue, false, null, null, message));
    }

    public static BadRequestValidationException toFieldError(String message, ParamSource source, String parameter, Object rejectedValue, String messageCode, Object... arguments) {
        return new BadRequestValidationException(new FieldError(source.getKey(), parameter, rejectedValue, false, new String[]{messageCode}, arguments, message));
    }

    public static BadRequestValidationException toFieldError(String message, String source, String field, Object rejectedValue) {
        return new BadRequestValidationException(new FieldError(source, field, rejectedValue, false, null, null, message));
    }

    public static BadRequestValidationException toFieldError(String message, String source, String field, Object rejectedValue, String messageCode, Object... arguments) {
        return new BadRequestValidationException(new FieldError(source, field, rejectedValue, false, new String[]{messageCode}, arguments, message));
    }

    /*
    public static BadRequestValidationException toObjectError(String objectName, String defaultMessage) {
        return new BadRequestValidationException(new ObjectError(objectName, defaultMessage));
    }

    public static BadRequestValidationException toObjectError(String objectName, String code, Object[] arguments, String defaultMessage) {
        return new BadRequestValidationException(new ObjectError(objectName, new String[]{code}, arguments, defaultMessage));
    }
    */

}
