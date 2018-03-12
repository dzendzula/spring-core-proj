package net.servodata.app.domain.commons.bo;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractAuditableBo extends AbstractBo implements AuditableBo {

    // --- fields ---

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;
    // --- methods ---

    public void touch() {
        onUpdate();
    }

    @PrePersist
    public void onInsert() {
        String username = SecurityUtils.getUsername();
        setCreateBy(username);
        setLastModifiedBy(username);
        OffsetDateTime sysdate = OffsetDateTime.now();
        setCreatedDate(sysdate);
        setLastModifiedDate(sysdate);
    }

    @PreUpdate
    public void onUpdate() {
        setLastModifiedBy(SecurityUtils.getUsername());
        setLastModifiedDate(OffsetDateTime.now());
    }

}