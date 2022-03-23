package com.datalogic.dlapos.confighelper.configurations.accessor;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import com.datalogic.dlapos.commons.support.APosException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class EciHelperTest {

    ConfigDatabase _testDB;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        _testDB = Room.inMemoryDatabaseBuilder(context, ConfigDatabase.class).build();
    }

    @After
    public void tearDown() {
        _testDB.close();
    }

    @Test
    public void loadFromAssets() throws APosException {
        EciHelper eciHelper = new EciHelper(_testDB);
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        eciHelper.initialize(context);
        assertThat(eciHelper.getJavaCode(6)).isEqualTo("ISO8859_4");
        assertThat(eciHelper.getJavaCode(14)).isEqualTo("");
        assertThat(eciHelper.getJavaCode(13)).isEqualTo("Cp874");
        assertThat(eciHelper.getJavaCode(26)).isEqualTo("UTF8");
    }
}