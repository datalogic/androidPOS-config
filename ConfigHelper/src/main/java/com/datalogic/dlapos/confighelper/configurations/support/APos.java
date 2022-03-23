package com.datalogic.dlapos.confighelper.configurations.support;

import com.datalogic.dlapos.confighelper.util.ProfileTags;

import java.util.HashMap;

/**
 * Class containing specific information about AndroidPos.
 *
 * @author fpoli
 */
public class APos implements MappableConfiguration {
    private String category;
    private String version;

    /**
     * Default Constructor
     */
    APos() {

    }

    /**
     * Copy constructor.
     *
     * @param apos the APos object to copy.
     */
    APos(APos apos) {
        this.category = apos.category;
        this.version = apos.version;
    }

    //region Setters

    /**
     * Setter for the AndroidPos's category of the profile.
     *
     * @param category the desired category.
     */
    public synchronized void setCategory(String category) {
        this.category = category;
    }

    /**
     * Setter for the version of the AndroidPos.
     *
     * @param version the desired version.
     */
    public synchronized void setVersion(String version) {
        this.version = version;
    }

    //endregion

    //region Getters

    /**
     * Getter for the AndroidPos category of the profile.
     *
     * @return the category of the AndroidPos's profile.
     */
    String getCategory() {
        return category;
    }

    /**
     * Getter for the version of the AndroidPos.
     *
     * @return the version of the AndroidPos.
     */
    String getVersion() {
        return version;
    }
    //endregion

    @Override
    public synchronized HashMap<String, String> toHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put(ProfileTags.APOS_CATEGORY, category);
        result.put(ProfileTags.APOS_VERSION, version);
        return result;
    }
}
