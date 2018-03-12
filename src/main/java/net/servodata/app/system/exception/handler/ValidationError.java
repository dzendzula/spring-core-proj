package net.servodata.app.system.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

/**
 * @author <a href="mailto:spilar@servodata.net">Martin Spilar</a>
 */
@Getter
public class ValidationError {

    // --- fields ---

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MessageSourceResolvable> errors;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ObjectError> objects;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FieldError> fields;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String errorMessage;

    // --- constructor ---

    public ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ValidationError(String errorMessage, MessageSourceResolvable error) {
        this.errorMessage = errorMessage;
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

    public ValidationError(String errorMessage, List<MessageSourceResolvable> errors) {
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public ValidationError(String errorMessage, List<ObjectError> objects, List<FieldError> fields) {
        this.errorMessage = errorMessage;
        this.objects = objects;
        this.fields = fields;
    }

    // --- methods ---

    /*
    public void addMessage(MessageSourceResolvable message) {
        add(message);
    }

    public void addObjectError(ObjectError objectError) {
        add(objectError);
    }

    public void addFieldError(FieldError fieldError) {
        add(fieldError);
    }

    public void add(MessageSourceResolvable error) {
        if (error instanceof FieldError) {
            if (fields == null) fields = new ArrayList<>();
            fields.add((FieldError) error);
        } else if (error instanceof ObjectError) {
            if (objects == null) objects = new ArrayList<>();
            objects.add((ObjectError) error);
        } else {
            if (errors == null) errors = new ArrayList<>();
            errors.add(error);
        }
    }
    */

}
