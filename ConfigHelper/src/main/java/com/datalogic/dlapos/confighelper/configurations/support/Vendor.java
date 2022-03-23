package com.datalogic.dlapos.confighelper.configurations.support;

import com.datalogic.dlapos.confighelper.util.ProfileTags;

import java.util.HashMap;

/**
 * Class containing vendor specific information.
 *
 * @author fpoli
 */
public class Vendor implements MappableConfiguration {
    private String name;
    private String url;

    /**
     * Default constructor.
     */
    Vendor() {

    }

    /**
     * Copy constructor.
     *
     * @param vendor the Vendor object to copy.
     */
    Vendor(Vendor vendor) {
        this.name = vendor.name;
        this.url = vendor.url;
    }

    //region Setters

    /**
     * Setter for vendor's name.
     *
     * @param name the desired vendor's name.
     */
    public synchronized void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for vendor's website url.
     *
     * @param url the desired vendor's website url.
     */
    public synchronized void setUrl(String url) {
        this.url = url;
    }
    //endregion

    //region Getters

    /**
     * Getter for vendor's name.
     *
     * @return the vendor's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the vendor's website URL.
     *
     * @return the vendor's website URL.
     */
    public String getUrl() {
        return url;
    }
    //endregion

    @Override
    public synchronized HashMap<String, String> toHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put(ProfileTags.VENDOR_NAME, name);
        result.put(ProfileTags.VENDOR_URL, url);
        return result;
    }
}
