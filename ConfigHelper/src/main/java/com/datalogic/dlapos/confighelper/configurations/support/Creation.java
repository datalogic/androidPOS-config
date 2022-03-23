package com.datalogic.dlapos.confighelper.configurations.support;

import com.datalogic.dlapos.confighelper.util.ProfileTags;

import java.util.HashMap;

/**
 * Class containing service's creation information.
 *
 * @author fpoli
 */
public class Creation implements MappableConfiguration {
    private String factoryClass;
    private String serviceClass;

    /**
     * Default constructor.
     */
    Creation() {
    }

    /**
     * Copy constructor.
     *
     * @param creation the Creation object to copy.
     */
    Creation(Creation creation) {
        this.factoryClass = creation.factoryClass;
        this.serviceClass = creation.serviceClass;
    }

    //region Setters

    /**
     * Setter for the factory responsible to instantiate the service object.
     *
     * @param factoryClass the factory class name.
     */
    public synchronized void setFactoryClass(String factoryClass) {
        this.factoryClass = factoryClass;
    }

    /**
     * Setter for the service class to instantiate for this profile.
     *
     * @param serviceClass the service class full package name.
     */
    public synchronized void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }
    //endregion

    //region Getters

    /**
     * Getter for the factory responsible to instantiate the service object.
     *
     * @return the factory full package name.
     */
    public String getFactoryClass() {
        return factoryClass;
    }

    /**
     * Getter for the service class to instantiate for this profile.
     *
     * @return the service class full package name.
     */
    public String getServiceClass() {
        return serviceClass;
    }
    //endRegion

    @Override
    public synchronized HashMap<String, String> toHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put(ProfileTags.SERVICE_CLASS, serviceClass);
        result.put(ProfileTags.SERVICE_INSTANCE_FACTORY, factoryClass);
        return result;
    }
}
