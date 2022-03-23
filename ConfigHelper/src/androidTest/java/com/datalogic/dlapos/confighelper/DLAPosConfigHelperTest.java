package com.datalogic.dlapos.confighelper;

import android.content.Context;

import androidx.core.util.Pair;
import androidx.test.core.app.ApplicationProvider;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.configurations.accessor.IhsHelper;
import com.datalogic.dlapos.confighelper.configurations.accessor.LabelHelper;
import com.datalogic.dlapos.confighelper.configurations.accessor.LabelIds;
import com.datalogic.dlapos.confighelper.configurations.support.DLAPosProfile;
import com.datalogic.dlapos.confighelper.util.ProfileTags;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class DLAPosConfigHelperTest {

    Context context = ApplicationProvider.getApplicationContext();
    DLAPosConfigHelper helper;

    @Before
    public void setUp() throws APosException {
        helper = new DLAPosConfigHelper(context);
        helper.initialize(context);
    }

    //region Profiles data

    //region Reads
    @Test
    public void checkDLPortalScannerService() throws APosException {
        DLAPosProfile profile = helper.getProfileManager().getConfigurationForProfileId("DL-Portal-Scanner");
        assertThat(profile.getProperty(ProfileTags.PROFILE_ID)).isEqualTo("DL-Portal-Scanner");
    }

    @Test(expected = APosException.class)
    public void askForInexistentProfile() throws APosException {
        helper.getProfileManager().getConfigurationForProfileId("NOT_EXISTENT");
    }

    @Test(expected = APosException.class)
    public void askForProfileWhenNotInitialized() throws APosException {
        helper.getProfileManager().deleteAllProfiles();
        helper.getProfileManager().getConfigurationForProfileId("DL-Portal-Scanner");
    }
    //endregion

    //region Connect to services
    @Test
    public void connectDLPortalScannerService() throws APosException {
        ServiceConnector connector = helper.getServiceConnectorForProfileId("DL-Portal-Scanner");
        connector.connect(context);
        assertThat(connector.getService() instanceof DLPortalScannerService).isTrue();
    }

    @Test
    public void connectGM4100Service() throws APosException {
        ServiceConnector connector = helper.getServiceConnectorForProfileId("DLS-GM4100-USB-OEM");
        connector.connect(context);
        assertThat(connector.getService() instanceof GM4100Service).isTrue();
    }

    @Test(expected = APosException.class)
    public void connectToMissingService() throws APosException {
        ServiceConnector connector = helper.getServiceConnectorForProfileId("NOT_A_VALID_PROFILE");
        connector.connect(context);
    }

    @Test(expected = APosException.class)
    public void connectToServiceFactoryDoesNotExists() throws APosException {
        ServiceConnector connector = helper.getServiceConnectorForProfileId("FACTORY_NOT_EXISTS");
        connector.connect(context);
    }

    @Test(expected = APosException.class)
    public void connectToInexistentService() throws APosException {
        ServiceConnector connector = helper.getServiceConnectorForProfileId("SERVICE_NOT_EXISTS");
        connector.connect(context);
    }

    @Test(expected = APosException.class)
    public void connectToServiceWhileNotInitialize() throws APosException {
        helper.getProfileManager().deleteAllProfiles();
        ServiceConnector connector = helper.getServiceConnectorForProfileId("DL-Portal-Scanner");
        connector.connect(context);
    }

    //endregion

    //endregion

    //region IHSHelper

    @Test
    public void getInformationMFieldValue() {
        String value = helper.getIhsHelper().getFieldName("M", IhsHelper.FrameType.INFORMATION);
        assertThat(value).isEqualTo("TopModelNumber");
    }

    @Test
    public void parseI20FrameTag() {
        Pair<String, String> result = helper.getIhsHelper().parse("I", "20", IhsHelper.FrameType.INFORMATION);
        assertThat(result.first).isEqualTo("Interface");
        assertThat(result.second).isEqualTo("SCRS232");
    }

    //endregion

    //region Label helper
    @Test
    public void getLabelIdsFromUSCODE() {
        helper.getLabelHelper().setDecodeType(LabelHelper.DecodeType.USCODE);
        LabelIds ids = helper.getLabelHelper().getLabelsIds(new byte[]{0x53});
        assertThat(ids.getUposId()).isEqualTo(107);
    }

    @Test
    public void getLabelIdsFromEUCODE() {
        helper.getLabelHelper().setDecodeType(LabelHelper.DecodeType.EUCODE);
        LabelIds ids = helper.getLabelHelper().getLabelsIds(new byte[]{0x6F});
        assertThat(ids.getUposId()).isEqualTo(144);
    }
    //endregion

    //region ECIHelper
    @Test
    public void getJavaCodeFor() {
        String javaCode = helper.getEciHelper().getJavaCode(3);
        assertThat(javaCode).isEqualTo("ISO8859_1");
    }
    //endregion
}