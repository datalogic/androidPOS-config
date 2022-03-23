package com.datalogic.dlapos.confighelper.util;

/**
 * File containing the commons tags to access the hashmap representation of a profile.
 *
 * @author fpoli
 */
public final class ProfileTags {

    private ProfileTags() {
    }

    /**
     * Tag identifying the profile ID.
     */
    public static final String PROFILE_ID = "logicalName";
    /**
     * Tag identifying the name of the service factory class to use.
     */
    public static final String SERVICE_INSTANCE_FACTORY = "serviceInstanceFactoryClass";
    /**
     * Tag identifying the name of the service class to use.
     */
    public static final String SERVICE_CLASS = "serviceClass";
    /**
     * Tag identifying the vendor name.
     */
    public static final String VENDOR_NAME = "vendorName";
    /**
     * Tag identifying the URL of the website of the vendor.
     */
    public static final String VENDOR_URL = "vendorURL";
    /**
     * Tag identifying the APOS version.
     */
    public static final String APOS_VERSION = "jposVersion";
    /**
     * Tag identifying the APOS device category.
     */
    public static final String APOS_CATEGORY = "deviceCategory";
    /**
     * Tag identifying the product name.
     */
    public static final String PRODUCT_NAME = "productName";
    /**
     * Tag identifying the product description.
     */
    public static final String PRODUCT_DESCRIPTION = "productDescription";
    /**
     * Tag identifying the URL of a website related to the product.
     */
    public static final String PRODUCT_URL = "productURL";
}