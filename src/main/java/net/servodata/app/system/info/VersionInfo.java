package net.servodata.app.system.info;

import java.io.Serializable;
import java.net.URL;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Getter;

/**
 * @author <a href="mailto:petr.cermak@doxologic.com">Petr Cermak</a>
 * @version $Revision$ $Date$
 */
@Getter
public class VersionInfo implements Serializable {

    // --- fields ---

    private final String versionSpec;
    private final String versionImpl;
    private final String copyrightOwner;
    @JsonSerialize(using = ToStringSerializer.class)
    private final URL vendorUrl;
    @JsonIgnore
    private final int inceptionYear;

    // --- constructor ---

    public VersionInfo(String versionSpec, String versionImpl, String copyrightOwner, URL vendorUrl, int inceptionYear) {
        this.versionSpec = versionSpec;
        this.versionImpl = versionImpl;
        this.copyrightOwner = copyrightOwner;
        this.vendorUrl = vendorUrl;
        this.inceptionYear = inceptionYear;
    }

    // --- methods ---

    public String getVersionMessage() {
        return "KOMPOST Application Version: " + versionImpl;
    }

    public String getCopyrightMessage() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return "Copyright (c) " + inceptionYear + '-' + currentYear + ' ' + copyrightOwner + " All Rights Reserved.";
    }

    @Override
    public String toString() {
        return getVersionMessage();
    }

}
