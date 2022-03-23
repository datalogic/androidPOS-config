package com.datalogic.dlapos.confighelper;

import android.content.Context;

import com.datalogic.dlapos.commons.service.APosServiceInstance;
import com.datalogic.dlapos.commons.support.APosException;


/**
 * Interface defining the minimal set of methods a ServiceFactory must implement.
 *
 * @author fpoli
 */
public interface ServiceFactory {

    /**
     * Function to create a Service knowing its class full package name.
     *
     * @param context          the application context.
     * @param serviceClassName the full package name of the Service to create.
     * @return an instance of the required Service.
     * @throws APosException when it is not possible to instantiate the Service.
     */
    APosServiceInstance createService(Context context, String serviceClassName) throws APosException;
}
