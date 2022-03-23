package com.datalogic.dlapos.confighelper.configurations.support;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.util.ProfileTags;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class representing a profile configuration.
 *
 * @author fpoli
 */
public class ProfileConfiguration implements MappableConfiguration {
    private final Creation creation;
    private final Vendor vendor;
    private final APos apos;
    private final Product product;
    private final Object LOCK = new Object();

    @SerializedName("logicalName")
    private String profileID;
    private Property[] properties;

    //region Constructors

    /**
     * Default constructor.
     */
    public ProfileConfiguration() {
        this.profileID = null;
        this.creation = new Creation();
        this.vendor = new Vendor();
        this.apos = new APos();
        this.product = new Product();
        this.properties = null;
    }


    /**
     * Copy constructor.
     *
     * @param profileConfiguration the ProfileConfiguration object to copy.
     */
    ProfileConfiguration(ProfileConfiguration profileConfiguration) {
        this.profileID = profileConfiguration.profileID;
        this.creation = new Creation(profileConfiguration.creation);
        this.vendor = new Vendor(profileConfiguration.vendor);
        this.apos = new APos(profileConfiguration.apos);
        this.product = new Product(profileConfiguration.product);
        if (profileConfiguration.properties != null) {
            this.properties = new Property[profileConfiguration.properties.length];
            for (int i = 0; i < profileConfiguration.properties.length; i++) {
                this.properties[i] = new Property(profileConfiguration.properties[i]);
            }
        }
    }

    //endregion

    //region Getters

    /**
     * Getter for the profile Id.
     *
     * @return the profile Id.
     */
    public String getProfileID() {
        return profileID;
    }

    /**
     * Getter for the creation object, containing data about the creation of the Service responsible of the profile.
     *
     * @return a Creation object containing creation data.
     */
    public Creation getCreation() {
        return creation;
    }

    /**
     * Getter for the vendor object, containing data about the vendor of the specific device represented by the profile.
     *
     * @return a Vendor object containing vendor data.
     */
    public Vendor getVendor() {
        return vendor;
    }

    /**
     * Getter for AndroidPos object, containing data useful for the AndroidPos library.
     *
     * @return an Apos object containing Android Pos information.
     */
    public APos getAPos() {
        return apos;
    }


    /**
     * Getter fot Product object, containing data about the Product.
     *
     * @return a Product object containing a description of the product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Getter for custom properties collection.
     *
     * @return an array of custom properties.
     */
    public Property[] getProperties() {
        return properties;
    }

    //endregion

    //region Setters

    /**
     * Setter for the profile id.
     *
     * @param profileID the desired profile id.
     */
    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    void setProperties(Property[] properties) {
        this.properties = properties;
    }

    //endregion

    //region Modifiers

    /**
     * Function to update a property.
     *
     * @param propertyName  the name of the property to update.
     * @param propertyValue the desired value.
     * @throws APosException if one of the arguments is null or if the property does not exists.
     */
    void updateProperty(@NonNull String propertyName, @NonNull String propertyValue) throws APosException {
        synchronized (LOCK) {
            if (propertyName == null)
                throw new APosException("The property name can not be null.", new IllegalArgumentException());
            if (propertyValue == null)
                throw new APosException("Can not set a null value for " + propertyName, new IllegalArgumentException());
            switch (propertyName) {
                case ProfileTags.PROFILE_ID:
                    this.setProfileID(propertyValue);
                    break;
                case ProfileTags.SERVICE_INSTANCE_FACTORY:
                    this.getCreation().setFactoryClass(propertyValue);
                    break;
                case ProfileTags.SERVICE_CLASS:
                    this.getCreation().setServiceClass(propertyValue);
                    break;
                case ProfileTags.VENDOR_NAME:
                    this.getVendor().setName(propertyValue);
                    break;
                case ProfileTags.VENDOR_URL:
                    this.getVendor().setUrl(propertyValue);
                    break;
                case ProfileTags.APOS_CATEGORY:
                    this.getAPos().setCategory(propertyValue);
                    break;
                case ProfileTags.APOS_VERSION:
                    this.getAPos().setVersion(propertyValue);
                case ProfileTags.PRODUCT_NAME:
                    this.getProduct().setName(propertyValue);
                    break;
                case ProfileTags.PRODUCT_DESCRIPTION:
                    this.getProduct().setDescription(propertyValue);
                    break;
                case ProfileTags.PRODUCT_URL:
                    this.getProduct().setUrl(propertyValue);
                    break;
                default:
                    boolean found = false;
                    if (properties != null) {
                        for (Property prop : properties) {
                            if (prop.getName().equals(propertyName)) {
                                prop.setValue(propertyValue);
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found)
                        throw new APosException("Property does not exists.");
            }
        }
    }

    /**
     * Function to add a new property.
     *
     * @param propertyName  the name of the new property
     * @param propertyType  the type of the new property, it can be null if it is referred to a standard property.
     * @param propertyValue the desired value.
     * @throws APosException if one of the arguments is null.
     */
    void addProperty(@NonNull String propertyName, @Nullable String propertyType, @NonNull String propertyValue) throws APosException {
        synchronized (LOCK) {
            if (propertyName == null)
                throw new APosException("The property name can not be null.", new IllegalArgumentException());
            if (propertyValue == null)
                throw new APosException("Can not set a null value for " + propertyName, new IllegalArgumentException());
            try {
                updateProperty(propertyName, propertyValue);
            } catch (APosException e) {
                if (propertyType == null)
                    throw new APosException("Can not set a null type for " + propertyName, e);

                ArrayList<Property> temp = new ArrayList<>();
                if (properties != null)
                    temp.addAll(Arrays.asList(properties));
                temp.add(new Property(propertyName, propertyType, propertyValue));
                properties = new Property[temp.size()];
                properties = temp.toArray(properties);
            }
        }
    }

    /**
     * Function to remove a custom property.
     *
     * @param propertyName the name of the property to be removed.
     * @throws APosException if a null argument is passed.
     */
    public void removeProperty(@NonNull String propertyName) throws APosException {
        synchronized (LOCK) {
            if (propertyName == null)
                throw new APosException("The property name can not be null.", new IllegalArgumentException());
            ArrayList<Property> temp = new ArrayList<>();
            if (properties != null)
                temp.addAll(Arrays.asList(properties));
            temp.removeIf(t -> t.getName().equals(propertyName));
            properties = new Property[temp.size()];
            properties = temp.toArray(properties);
        }
    }
    //endregion

    //region Overrides
    @Override
    public HashMap<String, String> toHashMap() {
        synchronized (LOCK) {
            HashMap<String, String> result = new HashMap<>();
            if (profileID != null)
                result.put(ProfileTags.PROFILE_ID, profileID);
            if (creation != null)
                result.putAll(creation.toHashMap());
            if (vendor != null)
                result.putAll(vendor.toHashMap());
            if (apos != null)
                result.putAll(apos.toHashMap());
            if (product != null)
                result.putAll(product.toHashMap());
            if (properties != null)
                for (Property prop : properties) {
                    result.putAll(prop.toHashMap());
                }
            return result;
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        synchronized (LOCK) {
            return new Gson().toJson(this);
        }
    }
    //endregion
}
