package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.commons.support.APosException;
import com.datalogic.dlapos.confighelper.configurations.support.DLAPosProfile;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class JsonAssetsFileContentTest {

    //region Json test string
    private final String json = "{\n" +
            "  \"version\": \"1.0\",\n" +
            "  \"aPosEntries\": [\n" +
            "    {\n" +
            "      \"logicalName\": \"DL-Portal-Scanner\",\n" +
            "      \"creation\": {\n" +
            "        \"factoryClass\": \"com.dls.jpos.service.DLSScannerInstanceFactory\",\n" +
            "        \"serviceClass\": \"com.dls.jpos.service.DLSPortalScannerService\"\n" +
            "      },\n" +
            "      \"vendor\": {\n" +
            "        \"name\": \"Datalogic ADC Inc.\",\n" +
            "        \"url\": \"http://www.adc.datalogic.com\"\n" +
            "      },\n" +
            "      \"apos\": {\n" +
            "        \"category\": \"PortalScanner\",\n" +
            "        \"version\": \"1.13\"\n" +
            "      },\n" +
            "      \"product\": {\n" +
            "        \"description\": \"PortalScannerService\",\n" +
            "        \"name\": \"PortalScannerService\",\n" +
            "        \"url\": \"http://www.adc.datalogic.com\"\n" +
            "      },\n" +
            "      \"properties\": [\n" +
            "        {\n" +
            "          \"name\": \"canAcceptStatisticsCmd\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"False\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"canCompareFirmwareVersion\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"True\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"canNotifyPowerChange\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"False\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"canUpdateFirmware\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"True\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"decodeType\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"warhol\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"deviceBus\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"TCPIP\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"deviceClass\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"DLADCPortalScanner\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"deviceDescription\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"DLA Jade Portal Scanner\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"deviceName\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"DLA Portal Scanner\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"imageBuffers\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"1\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"ipAddress\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"10.1.10.168\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"ipPort\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"23959\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"laneNumber\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"0\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"MBeansEnabled\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"False\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"WMIEnabled\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"False\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"logicalName\": \"DLS-GM4100-USB-OEM\",\n" +
            "      \"creation\": {\n" +
            "        \"factoryClass\": \"com.dls.jpos.service.DLSScannerInstanceFactory\",\n" +
            "        \"serviceClass\": \"com.dls.jpos.service.DLSPortalScannerService\"\n" +
            "      },\n" +
            "      \"vendor\": {\n" +
            "        \"name\": \"DLA\",\n" +
            "        \"url\": \"http://www.adc.datalogic.com\"\n" +
            "      },\n" +
            "      \"jpos\": {\n" +
            "        \"category\": \"Scanner\",\n" +
            "        \"version\": \"1.13\"\n" +
            "      },\n" +
            "      \"product\": {\n" +
            "        \"description\": \"ScannerService\",\n" +
            "        \"name\": \"ScannerService\",\n" +
            "        \"url\": \"http://www.adc.datalogic.com\"\n" +
            "      },\n" +
            "      \"properties\": [\n" +
            "        {\n" +
            "          \"name\": \"canAcceptStatisticsCmd\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"True\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"canCompareFirmwareVersion\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"False\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"canNotifyPowerChange\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"True\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"canUpdateFirmware\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"False\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"deviceBus\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"USB\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"deviceClass\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"USBHHScanner\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"deviceDescription\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"DLS Gryphon M4100 Scanner\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"deviceName\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"DLS Gryphon M4100 Scanner\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"productId\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"1214\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"usage\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"4b00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"vendorId\",\n" +
            "          \"type\": \"String\",\n" +
            "          \"value\": \"05f9\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    //endregion

    @Test
    public void toProfileList() throws APosException {
        ProfileJsonHelper toTest = ProfileJsonHelper.load(bufferizeString(json));
        List<Profile> profiles = toTest.toProfileList();
        assertThat(profiles.size()).isEqualTo(2);
        assertThat(profiles.get(0).profileId).isEqualTo("DL-Portal-Scanner");
        assertThat(profiles.get(1).profileId).isEqualTo("DLS-GM4100-USB-OEM");
        DLAPosProfile firstProfile = DLAPosProfile.createFromJson(profiles.get(0).profile);
        DLAPosProfile secondProfile = DLAPosProfile.createFromJson(profiles.get(1).profile);
        assertThat(firstProfile.getAsProfileConfiguration().getProfileID()).isEqualTo(profiles.get(0).profileId);
        assertThat(secondProfile.getAsProfileConfiguration().getProfileID()).isEqualTo(profiles.get(1).profileId);
    }

    @Test
    public void load() throws APosException {
        ProfileJsonHelper toTest = ProfileJsonHelper.load(bufferizeString(json));
        assertThat(toTest.getVersion()).isEqualTo("1.0");
        assertThat(toTest.getAPosEntries().length).isEqualTo(2);
        assertThat(toTest.getAPosEntries()[0].getProfileID()).isEqualTo("DL-Portal-Scanner");
        assertThat(toTest.getAPosEntries()[1].getProfileID()).isEqualTo("DLS-GM4100-USB-OEM");
    }

    @Test(expected = APosException.class)
    public void loadNotAJson() throws APosException {
        ProfileJsonHelper.load(bufferizeString("MUST FAIL"));
    }

    private BufferedReader bufferizeString(String toBeBuffered) {
        Reader input = new StringReader(toBeBuffered);
        return new BufferedReader(input);
    }
}