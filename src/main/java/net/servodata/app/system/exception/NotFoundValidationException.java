package net.servodata.app.system.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import net.servodata.app.domain.commons.bo.IdentifiedBo;

import lombok.Getter;

/**
 * @author <a href="mailto:spilar@servodata.net">Martin Spilar</a>
 */
@Getter
public class NotFoundValidationException extends AbstractException {

    // --- fields ---

    private final Class<? extends IdentifiedBo> entity;

    private final MessageSourceResolvable error;

    // --- constructor ---

    /**
     * @deprecated todo[ER-5177]: odstranit konstruktor, az budou vsechny vyskyty vyresene pres BO/ID.
     * Mozna zustanou nejaka mista, kde se not found nevztahuje k nejake konkretni entite,
     * ale jsou tak nejak soucasti logiky, tam misto vyjimky pouzit ResponseEntity.notFound()
     * viz napr. controller /downloadRatingSummary a /outage
     */
    @Deprecated
    public NotFoundValidationException(String message) {
        super(message);
        entity = null;
        this.error = null;
    }

    private NotFoundValidationException(Class<? extends IdentifiedBo> entity, MessageSourceResolvable error) {
        super(error.getDefaultMessage());
        this.entity = entity;
        this.error = error;
    }

    public NotFoundValidationException(Class<? extends IdentifiedBo> entity, Long id) {
        this(entity, createError(entity, id));
    }

    public NotFoundValidationException(Class<? extends IdentifiedBo> entity, String path, Long param) {
        this(entity, createError(entity, path, param));
    }

    public NotFoundValidationException(Class<? extends IdentifiedBo> entity, String path, String param) {
        this(entity, createError(entity, path, param));
    }

    // --- methods ---

    private static MessageSourceResolvable createError(Class<? extends IdentifiedBo> entity, Long id) {
        String name = entity.getSimpleName();
        if (name.endsWith("Bo")) {
            name = name.substring(0, name.length() - 2);
        }
        String message = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(name), " ") + " not found [id=" + id + "]";
        return new DefaultMessageSourceResolvable(new String[]{"validation." + name + ".notFound"}, new Object[]{entity, id}, message);
    }

    private static MessageSourceResolvable createError(Class<? extends IdentifiedBo> entity, String path, Object param) {
        String name = entity.getSimpleName();
        if (name.endsWith("Bo")) {
            name = name.substring(0, name.length() - 2);
        }
        String message = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(name), " ") + " not found [" + path + "=" + param + "]";
        return new DefaultMessageSourceResolvable(new String[]{"validation." + name + ".notFound.path"}, new Object[]{entity, path, param}, message);
    }

}
