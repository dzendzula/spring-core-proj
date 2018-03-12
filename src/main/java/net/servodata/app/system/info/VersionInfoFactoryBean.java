package net.servodata.app.system.info;

import java.net.URL;
import java.util.Calendar;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:stepan.marek@doxologic.com">Stepan Marek</a>
 */
@Getter
@Setter
@Slf4j(topic = "net.servodata.kompost.common.system.VersionInfo")
public class VersionInfoFactoryBean implements InitializingBean, FactoryBean<VersionInfo> {

    /**
     * Oznaceni verze implementace.
     */
    private String implementationVersion;

    /**
     * Oznaceni verze specifikace.
     */
    private String specificationVersion;

    private String copyrightOwner;

    private URL vendorUrl;

    private int inceptionYear;

    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    private VersionInfo versionInfo;

    // --- methods ---

    @Override
    public void afterPropertiesSet() {

        versionInfo = new VersionInfo(
                getSpecificationVersion(),
                getImplementationVersion(),
                getCopyrightOwner(),
                getVendorUrl(),
                getInceptionYear());

        log.info(versionInfo.getVersionMessage());
        log.info(versionInfo.getCopyrightMessage());
    }

    @Override
    public VersionInfo getObject() {
        return versionInfo;
    }

    @Override
    public Class<?> getObjectType() {
        return VersionInfo.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
