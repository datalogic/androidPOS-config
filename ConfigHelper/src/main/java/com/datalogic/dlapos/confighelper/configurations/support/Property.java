package com.datalogic.dlapos.confighelper.configurations.support;

import java.util.HashMap;
import java.util.Objects;

/**
 * Class containing custom properties data.
 *
 * @author fpoli
 */
public class Property implements MappableConfiguration {
    private String name;
    private String type;
    private String value;

    Property(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public Property(Property property) {
        this(property.name, property.type, property.value);
    }

    //region Setters

    /**
     * Setter for the name of the property.
     *
     * @param name the desired name of the property
     */
    public synchronized void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the type of the property.
     *
     * @param type the desired type of the property.
     */
    public synchronized void setType(String type) {
        this.type = type;
    }

    /**
     * Setter for the value of the property.
     *
     * @param value the desired value of the property.
     */
    public synchronized void setValue(String value) {
        this.value = value;
    }
    //endregion

    //region Getters

    /**
     * Getter for the name of the property.
     *
     * @return the name of the property.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the type of the property.
     *
     * @return the type of the property.
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the value of the property.
     *
     * @return the value of the property.
     */
    public String getValue() {
        return value;
    }
    //endregion

    @Override
    public synchronized HashMap<String, String> toHashMap() {
        HashMap<String, String> result = new HashMap<>();
        result.put(name, value);
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
