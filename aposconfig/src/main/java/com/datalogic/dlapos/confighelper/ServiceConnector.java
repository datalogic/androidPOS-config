package com.datalogic.dlapos.confighelper;

import android.content.Context;

import com.datalogic.dlapos.commons.service.APosServiceInstance;
import com.datalogic.dlapos.commons.support.APosException;

import java.util.HashMap;


/**
 * Class defining a connector for a specific service.
 *
 * @author fpoli
 */
public class ServiceConnector {
    private final String _profileID;
    private final String _serviceClass;
    private final String _factoryClass;
    private static final HashMap<String, ServiceFactory> _knownFactories = new HashMap<>();

    private APosServiceInstance _service;

    /**
     * Constructor for ServiceConnector
     *
     * @param profileID    the profileID of the service to be created.
     * @param factoryClass the full package name of the factory responsible to create the Service.
     * @param serviceClass the full package name of the Service Class to instantiate.
     */
    ServiceConnector(String profileID, String factoryClass, String serviceClass) {
        _profileID = profileID;
        _serviceClass = serviceClass;
        _factoryClass = factoryClass;
    }

    //region Getters

    /**
     * Getter for the Service handled by the connector.
     *
     * @return the service.
     */
    public APosServiceInstance getService() {
        return _service;
    }

    /**
     * Getter for the profile ID of the Service.
     *
     * @return the profile ID.
     */
    public String getProfileID() {
        return _profileID;
    }
    //endregion

    //region Setters

    //For test purpose only.
    void setService(APosServiceInstance service) {
        _service = service;
    }

    //endregion

    /**
     * Function to connect to the Service handled by the connector.
     *
     * @param context the application context.
     * @throws APosException when it is not possible to connect to the Service.
     */
    public void connect(Context context) throws APosException {
        if (!_knownFactories.containsKey(_factoryClass)) {
            try {
                Class<?> factoryClass = Class.forName(_factoryClass);
                ServiceFactory factory = (ServiceFactory) factoryClass.newInstance();
                _knownFactories.put(_factoryClass, factory);
            } catch (ClassNotFoundException e) {
                throw new APosException("Can not find the service factory class.", e);
            } catch (IllegalAccessException e) {
                throw new APosException("Can not access the service factory class.", e);
            } catch (InstantiationException e) {
                throw new APosException("Can not instantiate the service factory class.", e);
            } catch (ClassCastException e) {
                throw new APosException("Requested factory does not implement ServiceFactory", e);
            }
        }
        _service = _knownFactories.get(_factoryClass).createService(context, _serviceClass);
    }

    /**
     * Function to disconnect from the Service.
     *
     * @throws APosException when it is not possible to disconnect from the Service.
     */
    public void disconnect() throws APosException {
        if (_service != null) {
            _service.delete();
            _service = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        disconnect();
        super.finalize();
    }
}
