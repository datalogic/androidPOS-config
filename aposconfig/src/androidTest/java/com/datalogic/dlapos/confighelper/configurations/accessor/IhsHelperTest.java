package com.datalogic.dlapos.confighelper.configurations.accessor;

import android.content.Context;

import androidx.core.util.Pair;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.datalogic.dlapos.commons.support.APosException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class IhsHelperTest {
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

    //region getFieldName
    @Test
    public void getFieldName() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        assertThat(helper.getFieldName("m", IhsHelper.FrameType.INFORMATION)).isEqualTo("MainBoardNumber");
        assertThat(helper.getFieldName("m", IhsHelper.FrameType.HEALTH)).isEqualTo("MotorHealth");
        assertThat(helper.getFieldName("m", IhsHelper.FrameType.STATISTICS)).isEqualTo("MotorOnTime");
    }

    @Test
    public void getInexistentFieldName() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        assertThat(helper.getFieldName("NOT_EXISTS", IhsHelper.FrameType.INFORMATION)).isEqualTo("");
    }
    //endregion

    //region Parse
    @Test
    public void parseSimpleFrameId() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        Pair<String, String> result = helper.parse("m", "testContent", IhsHelper.FrameType.INFORMATION);
        assertThat(result.first).isEqualTo("MainBoardNumber");
        assertThat(result.second).isEqualTo("testContent");
    }

    @Test
    public void parseCompositeSimpleFrameId() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        Pair<String, String> result = helper.parse("I", "", IhsHelper.FrameType.INFORMATION);
        assertThat(result.first).isEqualTo("Interface");
        assertThat(result.second).isEqualTo("Unknown");
    }

    @Test
    public void parseCompositeFrameId() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        Pair<String, String> result = helper.parse("I", "5", IhsHelper.FrameType.INFORMATION);
        assertThat(result.first).isEqualTo("Interface");
        assertThat(result.second).isEqualTo("RS232");
    }

    @Test
    public void parseInexistentFrameId() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        Pair<String, String> result = helper.parse("NOT_EXISTS", "5", IhsHelper.FrameType.INFORMATION);
        assertThat(result).isNull();
    }
    //endregion

    //region ListIds
    @Test
    public void getHealthNames() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        List<String> result = helper.getAllFieldNamesForType(IhsHelper.FrameType.HEALTH);
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(15);
        assertThat(result.get(0)).isEqualTo("MotorHealth");
    }

    @Test
    public void getStatisticsNames() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        List<String> result = helper.getAllFieldNamesForType(IhsHelper.FrameType.STATISTICS);
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(28);
        assertThat(result.get(0)).isEqualTo("MotorOnTime");
    }

    @Test
    public void getInfoNames() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        List<String> result = helper.getAllFieldNamesForType(IhsHelper.FrameType.INFORMATION);
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(33);
        assertThat(result.get(0)).isEqualTo("ApplicationROM");
    }
    //endregion

    @Test
    public void loadFromAssets() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        assertThat(helper.getFieldName("m", IhsHelper.FrameType.INFORMATION)).isNotNull();
    }

    @Test
    public void doubleLoadFromAssets() throws APosException {
        Context context = ApplicationProvider.getApplicationContext();
        IhsHelper helper = new IhsHelper(_testDB);
        helper.initialize(context);
        assertThat(helper.getFieldName("m", IhsHelper.FrameType.INFORMATION)).isNotNull();
        helper.initialize(context);
    }
}