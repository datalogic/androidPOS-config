package com.datalogic.dlapos.confighelper;

import android.content.Context;

import com.datalogic.dlapos.commons.service.APosServiceInstance;
import com.datalogic.dlapos.commons.support.APosException;

public class TestFactory implements ServiceFactory {

    @Override
    public APosServiceInstance createService(Context context, String serviceClassName) throws APosException {
        try {
            Class<?> factoryClass = Class.forName(serviceClassName);
            return (APosServiceInstance) factoryClass.newInstance();
        } catch (Exception e) {
            throw new APosException("can not create service", e);
        }
    }
}