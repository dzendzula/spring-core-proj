package net.servodata.app.domain.commons.bo;

import java.time.OffsetDateTime;

public interface AuditableBo {

    String getCreateBy();

    void setCreateBy(String createBy);

    OffsetDateTime getCreatedDate();

    void setCreatedDate(OffsetDateTime createTime);

    String getLastModifiedBy();

    void setLastModifiedBy(String modifiedBy);

    OffsetDateTime getLastModifiedDate();

    void setLastModifiedDate(OffsetDateTime modifyTime);

}
