package net.servodata.app.system.info;

import java.beans.PropertyEditorSupport;
import java.text.Format;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author <a href="mailto:zhezhela@servodata.net">Oleksandr Zhezhela</a>
 */
@ControllerAdvice
public class ControllerAdviceInitBinder {

    private static class Editor<T> extends PropertyEditorSupport {

        private final Function<String, T> parser;
        private final Format format;

        public Editor(Function<String, T> parser, Format format) {

            this.parser = parser;
            this.format = format;
        }

        public void setAsText(String text) {
            setValue(this.parser.apply(text));
        }

        public String getAsText() {
            T obj = (T) getValue();
            if (obj != null) {
                return format.format(obj);
            }
            return "";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(OffsetDateTime.class,
                new Editor<>(
                        text -> OffsetDateTime.of(LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME), ZoneOffset.UTC),
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME.toFormat()));

        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}