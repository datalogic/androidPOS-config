package com.datalogic.dlapos.confighelper;

import android.content.Context;

import com.datalogic.dlapos.commons.service.APosServiceInstance;
import com.datalogic.dlapos.commons.support.APosException;

import org.junit.Test;
import org.mockito.Mock;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ServiceConnectorTest {

    @Mock
    Context mockContext = mock(Context.class);

    @Mock
    APosServiceInstance service = mock(APosServiceInstance.class);

    //region Connect
    @Test
    public void connect() throws APosException {
        ServiceConnector connector = new ServiceConnector("Test", TestFactory.class.getName(), TestService.class.getName());
        connector.connect(mockContext);
        assertThat(connector.getService() instanceof APosServiceInstance).isTrue();
    }

    @Test(expected = APosException.class)
    public void connectFactoryDoesNotExists() throws APosException {
        ServiceConnector connector = new ServiceConnector("Test", "I.Do.Not.Exist", TestService.class.getName());
        connector.connect(mockContext);
    }

    @Test(expected = APosException.class)
    public void connectFactoryIsNotAFactory() throws APosException {
        ServiceConnector connector = new ServiceConnector("Test", TestService.class.getName(), TestService.class.getName());
        connector.connect(mockContext);
    }
    //endregion

    //region Disconnect
    @Test
    public void disconnect() throws APosException {
        ServiceConnector connector = new ServiceConnector("Test", TestFactory.class.getName(), TestService.class.getName());
        connector.setService(service);
        connector.disconnect();
        verify(service, times(1)).delete();
    }

    @Test
    public void disconnectNullService() throws APosException {
        ServiceConnector connector = new ServiceConnector("Test", TestFactory.class.getName(), TestService.class.getName());
        connector.setService(null);
        connector.disconnect();
    }

    @Test(expected = APosException.class)
    public void disconnectException() throws APosException {
        doThrow(APosException.class).when(service).delete();
        ServiceConnector connector = new ServiceConnector("Test", TestFactory.class.getName(), TestService.class.getName());
        connector.setService(service);
        connector.disconnect();
    }
    //endregion

    //region Finalize
    @Test
    public void finalize() throws Throwable {
        ServiceConnector connector = new ServiceConnector("Test", TestFactory.class.getName(), TestService.class.getName());
        connector.setService(service);
        connector.finalize();
        verify(service, times(1)).delete();
    }
    //endregion

}