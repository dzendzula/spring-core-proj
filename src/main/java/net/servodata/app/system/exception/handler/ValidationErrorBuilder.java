package net.servodata.app.system.exception.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * @author <a href="mailto:spilar@servodata.net">Martin Spilar</a>
 */
public class ValidationErrorBuilder {

    public static ValidationError fromMessage(String errorMessage, MessageSourceResolvable message) {
        return new ValidationError(errorMessage, message);
    }

    public static ValidationError fromMessage(MessageSourceResolvable message) {
        return new ValidationError("Validation failed.", message);
    }

    public static ValidationError fromBindingErrors(BindingResult result) {
        return new ValidationError("Validation failed. " + result.getErrorCount() + " error(s)", result.getGlobalErrors(), result.getFieldErrors());
    }

    public static ValidationError fromError(ObjectError error) {
        List<ObjectError> objects = null;
        List<FieldError> fields = null;
        if (error instanceof FieldError) {
            fields = new ArrayList<>();
            fields.add((FieldError) error);
        } else if (error != null) {
            objects = new ArrayList<>();
            objects.add(error);
        }
        return new ValidationError("Validation failed.", objects, fields);
    }

    public static ValidationError fromConstraintViolations(Set<ConstraintViolation<?>> constraintViolations) {
        return new ValidationError("Validation failed. " + constraintViolations.size() + " error(s)", null, constraintViolations.stream()
                .map(constraintViolation -> new FieldError(constraintViolation.getRootBean().getClass().getName(), constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage())).collect(Collectors.toList()));
    }

}
