package com.datalogic.dlapos.confighelper.configurations.accessor;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import com.datalogic.dlapos.commons.constant.ScannerConstants;
import com.datalogic.dlapos.commons.support.APosException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class LabelHelperTest {
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
        LabelHelper labelHelper = new LabelHelper(_testDB);
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        labelHelper.initialize(context);
        assertThat(labelHelper.getLabelsIds(new byte[]{0x45}).getUposId()).isEqualTo(102);
        labelHelper.setDecodeType(LabelHelper.DecodeType.EUCODE);
        assertThat(labelHelper.getLabelsIds(new byte[]{0x55}).getUposName()).isEqualTo("SCAN_SDT_Code93");
        labelHelper.setDecodeType(LabelHelper.DecodeType.SCRS232);
        assertThat(labelHelper.getLabelsIds(new byte[]{0x5D, 0x43, 0x31}).getUposId()).isEqualTo(120);
        labelHelper.setDecodeType(LabelHelper.DecodeType.IBMUSBOEM);
        assertThat(labelHelper.getLabelsIds(new byte[]{0x00, 0x0A, 0x0B}).getTagName()).isEqualTo("CI_LABEL_ID_CODE39");
    }

    @Test
    public void doubleLoadFromAssets() throws APosException {
        LabelHelper labelHelper = new LabelHelper(_testDB);
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        labelHelper.initialize(context);
        labelHelper.initialize(context);
    }

    @Test
    public void getInvalidIdentifier() throws APosException {
        LabelHelper labelHelper = new LabelHelper(_testDB);
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        labelHelper.initialize(context);
        LabelIds ids = labelHelper.getLabelsIds(new byte[]{0x00, 0x00, 0x00});
        assertThat(ids.getUposId()).isEqualTo(ScannerConstants.SCAN_SDT_UNKNOWN);
    }
}