package com.datalogic.dlapos.confighelper.configurations.support;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.datalogic.dlapos.commons.support.APosException;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Manager of a profile.
 *
 * @author fpoli
 */
public class DLAPosProfile {

    private final Object LOCK = new Object();

    private ProfileConfiguration _profileConfiguration;
    private HashMap<String, String> _propertiesMap;

    /**
     * Function to load a profile from a json formatted string.
     *
     * @param json the json containing the profile.
     */
    public void load(String json) throws APosException {
        synchronized (LOCK) {
            clear();
            try {
                _profileConfiguration = new Gson().fromJson(json, ProfileConfiguration.class);
            } catch (Exception e) {
                throw new APosException("Can not parse the json File", e);
            }
        }
    }

    /**
     * Function to erase profile data.
     */
    public void clear() {
        _profileConfiguration = new ProfileConfiguration();
        _propertiesMap = null;
    }


    /**
     * Function to get the profile configuration as an object of type {@link ProfileConfiguration}.
     * Any modification of this object does not
     *
     * @return an object containing the configuration.
     */
    public ProfileConfiguration getAsProfileConfiguration() {
        synchronized (LOCK) {
            return new ProfileConfiguration(_profileConfiguration);
        }
    }

    //region Modifiers

    /**
     * Function to update a configuration.
     *
     * @param profileConfiguration the desired configuration.
     */
    public void updateProfile(ProfileConfiguration profileConfiguration) {
        synchronized (LOCK) {
            this._profileConfiguration = profileConfiguration;
            refresh();
        }
    }

    /**
     * Function to update a property.
     *
     * @param propertyName  the name of the property to update
     * @param propertyValue the desired value.
     * @throws APosException when one of the arguments is null or the property does not exists.
     */
    public void updateProperty(@NonNull String propertyName, @NonNull String propertyValue) throws APosException {
        synchronized (LOCK) {
            _profileConfiguration.updateProperty(propertyName, propertyValue);
            initMap();
        }
    }

    /**
     * Function to insert a new property.
     *
     * @param propertyName  the name of the property.
     * @param propertyType  the type of the property, it can be null on base properties.
     * @param propertyValue the desired value.
     * @throws APosException when one of the parameter is null.
     */
    public void addProperty(@NonNull String propertyName, @Nullable String propertyType, @NonNull String propertyValue) throws APosException {
        synchronized (LOCK) {
            _profileConfiguration.addProperty(propertyName, propertyType, propertyValue);
            initMap();
        }
    }

    /**
     * Function to remove a property.
     *
     * @param propertyName the name of the property to remove.
     * @throws APosException when a null property name is passed.
     */
    public void removeProperty(@NonNull String propertyName) throws APosException {
        synchronized (LOCK) {
            _profileConfiguration.removeProperty(propertyName);
            initMap();
        }
    }

    //endregion

    /**
     * Function to get a single property knowing the id of the property.
     *
     * @param propertyName the name of the property to be found.
     * @return the desired property if it exists, null otherwise.
     */
    public String getProperty(String propertyName) {
        synchronized (LOCK) {
            if (_propertiesMap == null) {
                initMap();
            }
            return _propertiesMap.get(propertyName);
        }
    }

    private void initMap() {
        _propertiesMap = _profileConfiguration.toHashMap();
    }

    private void refresh() {
        _propertiesMap = null;
        initMap();
    }

    public static DLAPosProfile createFromJson(String json) throws APosException {
        DLAPosProfile result = new DLAPosProfile();
        result.load(json);
        return result;
    }
}
