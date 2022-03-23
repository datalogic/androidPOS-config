package com.datalogic.dlapos.confighelper.configurations.support;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.util.ProfileTags;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

public class DLAPosProfileTest {

    //region Constants
    //region Commons
    private static final String SCANNER_FACTORY = "com.dls.jpos.service.DLSScannerInstanceFactory";
    private static final String SCANNER_SERVICE = "com.dls.jpos.service.DLSPortalScannerService";
    private static final String DL_URL = "http://www.adc.datalogic.com";
    private static final String JPOS_VERSION = "1.13";
    private static final String STRING_TYPE = "String";
    private static final String TRUE = "True";
    private static final String FALSE = "False";
    private static final String CAN_ACCEPT_STATIC_TAG = "canAcceptStatisticsCmd";
    private static final String CAN_COMPARE_FIRMWARE_TAG = "canCompareFirmwareVersion";
    private static final String CAN_NOTIFY_POWER_TAG = "canNotifyPowerChange";
    private static final String CAN_UPDATE_FW_TAG = "canUpdateFirmware";
    private static final String DEVICE_BUS_TAG = "deviceBus";
    private static final String DEVICE_CLASS_TAG = "deviceClass";
    private static final String DEVICE_DESCRIPTION_TAG = "deviceDescription";
    private static final String DEVICE_NAME_TAG = "deviceName";
    private static final String DECODE_TYPE_TAG = "decodeType";
    private static final String IMAGE_BUFFERS_TAG = "imageBuffers";
    private static final String IP_ADDRESS_TAG = "ipAddress";
    private static final String IP_PORT_TAG = "ipPort";
    private static final String LANE_NUMBER_TAG = "laneNumber";
    private static final String MBEANS_ENABLES_TAG = "MBeansEnabled";
    private static final String WMI_ENABLED_TAG = "WMIEnabled";
    private static final String PRODUCT_ID_TAG = "productId";
    private static final String USAGE_TAG = "usage";
    private static final String VENDOR_ID_TAG = "vendorId";
    //endregion
    //region First device
    private static final String FIRST_LOGICAL_NAME = "DL-Portal-Scanner";
    private static final String FIRST_VENDOR_NAME = "Datalogic ADC Inc.";
    private static final String FIRST_JPOS_CATEGORY = "PortalScanner";
    private static final String FIRST_PRODUCT = "PortalScannerService";
    private static final String FIRST_CAN_ACCEPT_STATIC_VALUE = FALSE;
    private static final String FIRST_CAN_COMPARE_FW_VALUE = TRUE;
    private static final String FIRST_CAN_NOTIFY_POWER_VALUE = FALSE;
    private static final String FIRST_CAN_UPDATE_FW_VALUE = TRUE;
    private static final String FIRST_DEVICE_BUS_VALUE = "TCPIP";
    private static final String FIRST_DEVICE_CLASS_VALUE = "DLADCPortalScanner";
    private static final String FIRST_DEVICE_DESCRIPTION_VALUE = "DLA Jade Portal Scanner";
    private static final String FIRST_DEVICE_NAME_VALUE = "DLA Portal Scanner";
    private static final String FIRST_DECODE_TYPE_VALUE = "warhol";
    private static final String FIRST_IMAGE_BUFFERS_VALUE = "1";
    private static final String FIRST_IP_ADDRESS_VALUE = "10.1.10.168";
    private static final String FIRST_IP_PORT_VALUE = "23959";
    private static final String FIRST_LANE_NUMBER_VALUE = "0";
    private static final String FIRST_MBEANS_ENABLES_VALUE = FALSE;
    private static final String FIRST_WMI_ENABLED_VALUE = FALSE;
    //endregion
    //region Second device
    private static final String SECOND_LOGICAL_NAME = "DLS-GM4100-USB-OEM";
    private static final String SECOND_VENDOR_NAME = "DLA";
    private static final String SECOND_JPOS_CATEGORY = "Scanner";
    private static final String SECOND_PRODUCT = "ScannerService";
    private static final String SECOND_CAN_ACCEPT_STATIC_VALUE = TRUE;
    private static final String SECOND_CAN_COMPARE_FW_VALUE = FALSE;
    private static final String SECOND_CAN_NOTIFY_POWER_VALUE = TRUE;
    private static final String SECOND_CAN_UPDATE_FW_VALUE = FALSE;
    private static final String SECOND_DEVICE_BUS_VALUE = "USB";
    private static final String SECOND_DEVICE_CLASS_VALUE = "USBHHScanner";
    private static final String SECOND_DEVICE_DESCRIPTION_VALUE = "DLS Gryphon M4100 Scanner";
    private static final String SECOND_DEVICE_NAME_VALUE = "DLS Gryphon M4100 Scanner";
    private static final String SECOND_PRODUCT_ID_VALUE = "1214";
    private static final String SECOND_USAGE_VALUE = "4b00";
    private static final String SECOND_VENDOR_ID_VALUE = "05f9";
    //endregion
    //endregion

    //region Profiles
    private final String profile1 =
            "{\n" +
                    "   \"logicalName\": \"" + FIRST_LOGICAL_NAME + "\",\n" +
                    "   \"creation\": {\n" +
                    "       \"factoryClass\": \"" + SCANNER_FACTORY + "\",\n" +
                    "       \"serviceClass\": \"" + SCANNER_SERVICE + "\"\n" +
                    "   },\n" +
                    "   \"vendor\": {\n" +
                    "       \"name\": \"" + FIRST_VENDOR_NAME + "\",\n" +
                    "       \"url\": \"" + DL_URL + "\"\n" +
                    "   },\n" +
                    "   \"apos\": {\n" +
                    "       \"category\": \"" + FIRST_JPOS_CATEGORY + "\",\n" +
                    "       \"version\": \"" + JPOS_VERSION + "\"\n" +
                    "   },\n" +
                    "   \"product\": {\n" +
                    "       \"description\": \"" + FIRST_PRODUCT + "\",\n" +
                    "       \"name\": \"" + FIRST_PRODUCT + "\",\n" +
                    "       \"url\": \"" + DL_URL + "\"\n" +
                    "   },\n" +
                    "   \"properties\": [\n" +
                    "       {\n" +
                    "           \"name\": \"" + CAN_ACCEPT_STATIC_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_CAN_ACCEPT_STATIC_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + CAN_COMPARE_FIRMWARE_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_CAN_COMPARE_FW_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + CAN_NOTIFY_POWER_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_CAN_NOTIFY_POWER_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + CAN_UPDATE_FW_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_CAN_UPDATE_FW_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + DECODE_TYPE_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_DECODE_TYPE_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + DEVICE_BUS_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_DEVICE_BUS_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + DEVICE_CLASS_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_DEVICE_CLASS_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + DEVICE_DESCRIPTION_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_DEVICE_DESCRIPTION_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + DEVICE_NAME_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_DEVICE_NAME_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + IMAGE_BUFFERS_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_IMAGE_BUFFERS_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + IP_ADDRESS_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_IP_ADDRESS_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + IP_PORT_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_IP_PORT_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + LANE_NUMBER_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_LANE_NUMBER_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + MBEANS_ENABLES_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_MBEANS_ENABLES_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + WMI_ENABLED_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + FIRST_WMI_ENABLED_VALUE + "\"\n" +
                    "       }\n" +
                    "   ]\n" +
                    "}";

    private final String profile2 =
            "{\n" +
                    "   \"logicalName\": \"" + SECOND_LOGICAL_NAME + "\",\n" +
                    "   \"creation\": {\n" +
                    "   \"factoryClass\": \"" + SCANNER_FACTORY + "\",\n" +
                    "       \"serviceClass\": \"" + SCANNER_SERVICE + "\"\n" +
                    "   },\n" +
                    "   \"vendor\": {\n" +
                    "       \"name\": \"" + SECOND_VENDOR_NAME + "\",\n" +
                    "       \"url\": \"" + DL_URL + "\"\n" +
                    "   },\n" +
                    "   \"apos\": {\n" +
                    "       \"category\": \"" + SECOND_JPOS_CATEGORY + "\",\n" +
                    "       \"version\": \"" + JPOS_VERSION + "\"\n" +
                    "   },\n" +
                    "   \"product\": {\n" +
                    "       \"description\": \"" + SECOND_PRODUCT + "\",\n" +
                    "       \"name\": \"" + SECOND_PRODUCT + "\",\n" +
                    "       \"url\": \"" + DL_URL + "\"\n" +
                    "   },\n" +
                    "   \"properties\": [\n" +
                    "       {\n" +
                    "           \"name\": \"" + CAN_ACCEPT_STATIC_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_CAN_ACCEPT_STATIC_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + CAN_COMPARE_FIRMWARE_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_CAN_COMPARE_FW_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + CAN_NOTIFY_POWER_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_CAN_NOTIFY_POWER_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + CAN_UPDATE_FW_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_CAN_UPDATE_FW_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + DEVICE_BUS_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_DEVICE_BUS_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + DEVICE_CLASS_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_DEVICE_CLASS_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + DEVICE_DESCRIPTION_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_DEVICE_DESCRIPTION_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + DEVICE_NAME_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_DEVICE_NAME_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + PRODUCT_ID_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_PRODUCT_ID_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + USAGE_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_USAGE_VALUE + "\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"name\": \"" + VENDOR_ID_TAG + "\",\n" +
                    "           \"type\": \"" + STRING_TYPE + "\",\n" +
                    "           \"value\": \"" + SECOND_VENDOR_ID_VALUE + "\"\n" +
                    "       }\n" +
                    "   ]\n" +
                    "}\n";

    //endregion

    //region Load
    @Test
    public void load() throws APosException {
        final DLAPosProfile testing = new DLAPosProfile();
        testing.load(profile1);
        assertThat(testing.getAsProfileConfiguration()).isNotNull();
    }

    @Test(expected = APosException.class)
    public void loadNotAJson() throws APosException {
        final DLAPosProfile testing = new DLAPosProfile();
        testing.load("SURELY NOT A JSON");
        assertThat(testing.getAsProfileConfiguration()).isNotNull();
    }
    //endregion

    //region Clear
    @Test
    public void clear() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getAsProfileConfiguration()).isNotNull();
        testing.clear();
        assertThat(testing.getAsProfileConfiguration().getProfileID()).isNull();
    }
    //endregion

    //region Updates
    @Test
    public void updateProfile() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getProperty(ProfileTags.PROFILE_ID)).isEqualTo(FIRST_LOGICAL_NAME);
        final ProfileConfiguration profileConfiguration = new ProfileConfiguration();
        profileConfiguration.setProfileID("TEST");
        testing.updateProfile(profileConfiguration);
        assertThat(testing.getProperty(ProfileTags.PROFILE_ID)).isEqualTo("TEST");
        assertThat(testing.getAsProfileConfiguration().getProfileID()).isEqualTo("TEST");

    }

    @Test
    public void updateProperty() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getProperty(ProfileTags.PROFILE_ID)).isEqualTo(FIRST_LOGICAL_NAME);
        testing.updateProperty(ProfileTags.PROFILE_ID, "Test");
        assertThat(testing.getProperty(ProfileTags.PROFILE_ID)).isEqualTo("Test");
        assertThat(testing.getAsProfileConfiguration().getProfileID()).isEqualTo("Test");
    }

    @Test
    public void updatePropertyNullValue() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        try {
            testing.updateProperty(ProfileTags.PROFILE_ID, null);
        } catch (APosException e) {
            assertThat(testing.getProperty(ProfileTags.PROFILE_ID)).isEqualTo(FIRST_LOGICAL_NAME);
            assertThat(testing.getAsProfileConfiguration().getProfileID()).isEqualTo(FIRST_LOGICAL_NAME);
            return;
        }
        fail("Exception expected");
    }

    @Test(expected = APosException.class)
    public void updatePropertyNullName() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        testing.updateProperty(null, "null");
    }

    @Test
    public void updateInexistentProperty() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        int propCount = testing.getAsProfileConfiguration().getProperties().length;
        try {
            testing.updateProperty("test", "test");
        } catch (APosException e) {
            assertThat(testing.getAsProfileConfiguration().getProperties().length).isEqualTo(propCount);
            assertThat(testing.getProperty("test")).isNull();
            return;
        }
        fail("Exception expected");
    }

    //endregion

    //region Adds

    @Test
    public void addProperty() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        int propCount = testing.getAsProfileConfiguration().getProperties().length;
        testing.addProperty("test", "String", "test");
        assertThat(testing.getAsProfileConfiguration().getProperties().length).isEqualTo(propCount + 1);
        assertThat(testing.getProperty("test")).isEqualTo("test");
        assertThat(testing.getAsProfileConfiguration().getProperties()[testing.getAsProfileConfiguration().getProperties().length - 1].getValue()).isEqualTo("test");
    }

    @Test
    public void addPropertyNullName() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        int propCount = testing.getAsProfileConfiguration().getProperties().length;
        try {
            testing.addProperty(null, "test", "test");
        } catch (APosException e) {
            assertThat(testing.getAsProfileConfiguration().getProperties().length).isEqualTo(propCount);
            return;
        }
        fail("Exception expected");
    }

    @Test
    public void addPropertyNullType() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        try {
            testing.addProperty("test", null, "test");
        } catch (APosException e) {
            assertThat(testing.getProperty("test")).isNull();
            return;
        }
        fail("Exception expected");
    }

    @Test
    public void addPropertyNullValue() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        try {
            testing.addProperty("test", "test", null);
        } catch (APosException e) {
            assertThat(testing.getProperty("test")).isNull();
            return;
        }
        fail("Exception expected");
    }

    //endregion

    //region Remove

    @Test
    public void removeProperty() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        int initialPropertiesCount = testing.getAsProfileConfiguration().getProperties().length;
        testing.removeProperty(MBEANS_ENABLES_TAG);
        assertThat(testing.getProperty(MBEANS_ENABLES_TAG)).isNull();
        assertThat(testing.getAsProfileConfiguration().getProperties().length).isEqualTo(initialPropertiesCount - 1);
    }

    @Test
    public void removePropertyNullName() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        int initialPropertiesCount = testing.getAsProfileConfiguration().getProperties().length;
        try {
            testing.removeProperty(null);
        } catch (APosException e) {
            assertThat(testing.getAsProfileConfiguration().getProperties().length).isEqualTo(initialPropertiesCount);
            return;
        }
        fail("Exception expected");
    }

    //endregion

    //region GetProperties
    @Test
    public void getPropertyLogicalName() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getProperty(ProfileTags.PROFILE_ID)).isEqualTo(FIRST_LOGICAL_NAME);
        testing.load(profile2);
        assertThat(testing.getProperty(ProfileTags.PROFILE_ID)).isEqualTo(SECOND_LOGICAL_NAME);
    }

    @Test
    public void getPropertyServiceClass() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getProperty(ProfileTags.SERVICE_CLASS)).isEqualTo(SCANNER_SERVICE);
        testing.load(profile2);
        assertThat(testing.getProperty(ProfileTags.SERVICE_CLASS)).isEqualTo(SCANNER_SERVICE);
    }

    @Test
    public void getPropertyVendorName() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getProperty(ProfileTags.VENDOR_NAME)).isEqualTo(FIRST_VENDOR_NAME);
        testing.load(profile2);
        assertThat(testing.getProperty(ProfileTags.VENDOR_NAME)).isEqualTo(SECOND_VENDOR_NAME);
    }

    @Test
    public void getPropertyAposCategory() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getProperty(ProfileTags.APOS_CATEGORY)).isEqualTo(FIRST_JPOS_CATEGORY);
        testing.load(profile2);
        assertThat(testing.getProperty(ProfileTags.APOS_CATEGORY)).isEqualTo(SECOND_JPOS_CATEGORY);
    }

    @Test
    public void getPropertyProductName() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getProperty(ProfileTags.PRODUCT_NAME)).isEqualTo(FIRST_PRODUCT);
        testing.load(profile2);
        assertThat(testing.getProperty(ProfileTags.PRODUCT_DESCRIPTION)).isEqualTo(SECOND_PRODUCT);
    }

    @Test
    public void getPropertyDeviceBus() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getProperty(DEVICE_BUS_TAG)).isEqualTo(FIRST_DEVICE_BUS_VALUE);
        testing.load(profile2);
        assertThat(testing.getProperty(DEVICE_BUS_TAG)).isEqualTo(SECOND_DEVICE_BUS_VALUE);
    }

    @Test
    public void getPropertyProductID() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        assertThat(testing.getProperty(PRODUCT_ID_TAG)).isNull();
        testing.load(profile2);
        assertThat(testing.getProperty(PRODUCT_ID_TAG)).isEqualTo(SECOND_PRODUCT_ID_VALUE);
    }
    //endregion

    //region GetProfile

    @Test
    public void getAsProfileConfiguration() throws APosException {
        final DLAPosProfile testing = DLAPosProfile.createFromJson(profile1);
        final ProfileConfiguration conf = testing.getAsProfileConfiguration();
        testing.updateProperty(ProfileTags.PROFILE_ID, "ORIGINAL");
        assertThat(testing.getAsProfileConfiguration().getProfileID()).isEqualTo("ORIGINAL");
        assertThat(conf.getProfileID()).isEqualTo(FIRST_LOGICAL_NAME);
        conf.setProfileID("COPY");
        assertThat(conf.getProfileID()).isEqualTo("COPY");
        assertThat(testing.getAsProfileConfiguration().getProfileID()).isEqualTo("ORIGINAL");
    }

    //endregion
}