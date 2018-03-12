package net.servodata.app.system.exception.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import net.servodata.app.system.exception.AuthorizationException;
import net.servodata.app.system.exception.BadRequestValidationException;
import net.servodata.app.system.exception.ConflictValidationException;
import net.servodata.app.system.exception.NotFoundValidationException;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.*;

/**
 * @author <a href="mailto:spilar@servodata.net">Martin Spilar</a>
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ValidationError methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("Validation exception: " + ex);
        FieldError error = new FieldError("request", ex.getName(), ex.getValue(), true, new String[]{"validation.global.typeMismatch"}, new Object[]{ex.getRequiredType(), ex.getValue()}, ex.getMessage());
        return ValidationErrorBuilder.fromError(error);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
    @ResponseBody
    public ValidationError methodArgumentConversionNotSupportedException(MethodArgumentConversionNotSupportedException ex) {
        log.warn("Validation exception: " + ex);
        FieldError error = new FieldError("request", ex.getName(), ex.getValue(), true, new String[]{"validation.global.typeMismatch"}, new Object[]{ex.getRequiredType(), ex.getValue()}, ex.getMessage());
        return ValidationErrorBuilder.fromError(error);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public ValidationError typeMismatchException(TypeMismatchException ex) {
        log.warn("Validation exception: " + ex);
        ObjectError error = new ObjectError("request", new String[]{"validation.global.typeMismatch"}, new Object[]{ex.getRequiredType(), ex.getValue()}, ex.getMessage());
        return ValidationErrorBuilder.fromError(error);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ValidationError typeMismatchException(HttpMessageNotReadableException root) {
        Throwable ex = root.getCause();
        log.warn("Validation exception: " + ex);
        if (ex instanceof JsonMappingException) {
            JsonMappingException jme = (JsonMappingException) ex;
            Object[] objects = null;
            if (ex instanceof InvalidFormatException) {
                InvalidFormatException ife = (InvalidFormatException) ex;
                objects = new Object[]{ife.getTargetType(), ife.getValue()};
            }
            ObjectError error = new ObjectError("request", new String[]{"validation.global.typeMismatch"}, objects, jme.getOriginalMessage() + ": " + jme.getPathReference());
            return ValidationErrorBuilder.fromError(error);
        }
        ObjectError error = new ObjectError("request", ex.getMessage());
        return ValidationErrorBuilder.fromError(error);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ValidationError methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("Validation exception: " + ex);
        return ValidationErrorBuilder.fromBindingErrors(ex.getBindingResult());
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ValidationError constraintViolationException(ConstraintViolationException ex) {
        log.warn("Validation exception: " + ex);
        return ValidationErrorBuilder.fromConstraintViolations(ex.getConstraintViolations());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundValidationException.class)
    @ResponseBody
    public ValidationError notFoundValidationException(NotFoundValidationException ex) {
        log.warn("Validation exception: " + ex);
        MessageSourceResolvable error = ex.getError();
        if (error != null) {
            return ValidationErrorBuilder.fromMessage(error);
        } else {
            return ValidationErrorBuilder.fromError(new ObjectError(ex.getClass().getSimpleName(), ex.getMessage()));
        }
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestValidationException.class)
    @ResponseBody
    public ValidationError badRequestValidationException(BadRequestValidationException ex) {
        log.warn("Validation exception: " + ex);
        ObjectError error = ex.getError();
        if (error != null) {
            return ValidationErrorBuilder.fromError(error);
        } else {
            return ValidationErrorBuilder.fromError(new ObjectError(ex.getClass().getSimpleName(), ex.getMessage()));
        }
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(ConflictValidationException.class)
    @ResponseBody
    public ValidationError conflictValidationException(ConflictValidationException ex) {
        log.warn("Validation exception: " + ex);
        MessageSourceResolvable error = ex.getError();
        if (error != null) {
            return ValidationErrorBuilder.fromMessage(error);
        } else {
            return ValidationErrorBuilder.fromError(new ObjectError(ex.getClass().getSimpleName(), ex.getMessage()));
        }
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public ValidationError authorizationException(AuthorizationException ex) {
        log.warn("Access denied exception: " + ex);
        MessageSourceResolvable error = ex.getError();
        if (error == null) {
            error = AuthorizationException.DEFAULT_MESSAGE;
        }
        return ValidationErrorBuilder.fromMessage(error);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MultipartException.class)
    @ResponseBody

    public ValidationError multipartException(MultipartException ex) {
        log.warn("Multipart exception: " + ex);
        ObjectError error = new ObjectError(ex.getClass().getSimpleName(), "validation.global.multipartException");
        return ValidationErrorBuilder.fromError(error);
    }

}
