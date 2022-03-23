package com.datalogic.dlapos.confighelper.configurations.support;

import com.datalogic.dlapos.confighelper.util.ProfileTags;

import java.util.HashMap;

/**
 * Class containing the product description.
 *
 * @author fpoli
 */
public class Product implements MappableConfiguration {
    private String description;
    private String name;
    private String url;

    /**
     * Default constructor.
     */
    Product() {
    }

    /**
     * Copy constructor.
     *
     * @param product the Product object to copy.
     */
    Product(Product product) {
        this.description = product.description;
        this.name = product.name;
        this.url = product.url;
    }

    //region Setters

    /**
     * Setter for the description of the product.
     *
     * @param description the desired description of the product.
     */
    public synchronized void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for the name of the product.
     *
     * @param name the desired name of the product.
     */
    public synchronized void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for an URL related to the product.
     *
     * @param url the desired URL.
     */
    public synchronized void setUrl(String url) {
        this.url = url;
    }

    //endregion

    //region Getters

    /**
     * Getter for the description of the product.
     *
     * @return the description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the name of the product.
     *
     * @return the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the URL related to the product.
     *
     * @return the URL related to the product.
     */
    String getUrl() {
        return url;
    }
    //endregion

    @Override
    public synchronized HashMap<String, String> toHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put(ProfileTags.PRODUCT_NAME, name);
        result.put(ProfileTags.PRODUCT_DESCRIPTION, description);
        result.put(ProfileTags.PRODUCT_URL, url);
        return result;
    }
}
