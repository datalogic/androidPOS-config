package com.datalogic.dlapos.confighelper.configurations.accessor;

import com.datalogic.dlapos.commons.support.APosException;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class LabelIdsCSVHelperTest {

    private static final String LABELS_CSV = "//UPOS Identifier Names,UPOS IDs,Tag Name,USA (code),EU (code),IBM-USB-OEM,SCRS232,Output Prefix,Output Suffix, Version Number = 1.1.3\n" +
            "SCAN_SDT_Codabar,107,CI_LABEL_ID_ABC_CODABAR,53,53,,,,,\n" +
            "SCAN_SDT_PLESSEY,144,CI_LABEL_ID_ANKER_PLESSEY,6F,6F,,,,,\n" +
            "SCAN_SDT_AZTEC,206,CI_LABEL_ID_AZTEC,417A,21,00340B,417A,,,\n" +
            "SCAN_SDT_OTHER,501,CI_LABEL_ID_BC412,39,39,,,,,\n" +
            "SCAN_SDT_HANXIN,215,CI_LABEL_ID_CHINA_SENSIBLE_CODE,2453,2453,,,,,\n" +
            "SCAN_SDT_Codabar,107,CI_LABEL_ID_CODABAR,25,52,000E0B,25,,,\n" +
            "SCAN_SDT_EAN13,104,CI_LABEL_ID_ISSN,6E,6E,,,,,\n" +
            "SCAN_SDT_CodablockA,212,CI_LABEL_ID_CODABLOCK_A,6E,6E,,,,,\n" +
            "SCAN_SDT_CodablockF,213,CI_LABEL_ID_CODABLOCK_F,6C,6D,,,,,\n" +
            "SCAN_SDT_Code11,142,CI_LABEL_ID_CODE11,4345,62,,,,,\n" +
            "SCAN_SDT_Code128,110,CI_LABEL_ID_CODE128,23,54,00180B,4233,,,\n" +
            "SCAN_SDT_Code16k,211,CI_LABEL_ID_CODE16K,70,70,,,,,\n" +
            "SCAN_SDT_Code39,108,CI_LABEL_ID_CODE39,2A,56,000A0B,4231,,,\n" +
            "SCAN_SDT_CodeCIP,139,CI_LABEL_ID_CODE39_CIP,59,59,,,,,\n" +
            "SCAN_SDT_Code39,108,CI_LABEL_ID_CODE39_DANISH_PPT,2459,2459,,,,,\n" +
            "SCAN_SDT_Code39,108,CI_LABEL_ID_CODE39_LAPOSTE,2461,2461,,,,,\n" +
            "SCAN_SDT_Code39,108,CI_LABEL_ID_CODE39_PZN,245A,245A,,,,,\n" +
            "SCAN_SDT_OTHER,501,CI_LABEL_ID_CODE4,34,34,,,,,\n" +
            "SCAN_SDT_Code49,210,CI_LABEL_ID_CODE49,71,71,00350B,,,,\n" +
            "SCAN_SDT_OTHER,501,CI_LABEL_ID_CODE5,6A,6A,,,,,\n" +
            "SCAN_SDT_Code93,109,CI_LABEL_ID_CODE93,26,55,00190B,26,,,\n" +
            "SCAN_SDT_ITF_CK,133,CI_LABEL_ID_COMPRESSED_2OF5,68,68,,,,,\n" +
            "SCAN_SDT_RSS14,131,CI_LABEL_ID_DATABAR_14,5234,75,002A0B,5234,,,\n" +
            "SCAN_SDT_GS1DATABAR,131,CI_LABEL_ID_DATABAR_14_COMPOSITE,5234,63,002A0B,5234,,,\n" +
            "SCAN_SDT_RSS_EXPANDED ,132,CI_LABEL_ID_DATABAR_EXPANDED,5258,74,002B0B,5258,,,\n" +
            "SCAN_SDT_GS1DATABAR_E,132,CI_LABEL_ID_DATABAR_EXPANDED_COMPOSITE,5258,64,002B0B,5258,,,\n" +
            "SCAN_SDT_GS1DATABAR_TYPE2,134,CI_LABEL_ID_DATABAR_LIMITED,524C,76,002A0B,524C,,,\n" +
            "SCAN_SDT_GS1DATABAR_TYPE2,134,CI_LABEL_ID_DATABAR_LIMITED_COMPOSITE,524C,69,002A0B,524C,,,\n" +
            "SCAN_SDT_TF,105,CI_LABEL_ID_S25,73,50,000C0B,73,,,\n" +
            "SCAN_SDT_TF,105,CI_LABEL_ID_DATALOGIC_2OF5,73,73,,,,,\n" +
            "SCAN_SDT_DATAMATRIX,203,CI_LABEL_ID_DATAMATRIX,446D,77,00320B,446D,,,\n" +
            "SCAN_SDT_EAN128,120,CI_LABEL_ID_EAN128,5D4331,6B,00250B,5D4331,,,\n" +
            "SCAN_SDT_EAN128,120,CI_LABEL_ID_EAN128_COMPOSITE,5D4331,2445,,5D4331,,,\n" +
            "SCAN_SDT_EAN13,104,CI_LABEL_ID_EAN13,46,42,16,46,,,\n" +
            "SCAN_SDT_EAN13_S,119,CI_LABEL_ID_EAN13_COMPOSITE,46,2446,00230B,46,,,\n" +
            "SCAN_SDT_EAN13_S,119,CI_LABEL_ID_EAN13_P2,46,4C,00130B,46,,,\n" +
            "SCAN_SDT_EAN13_S,119,CI_LABEL_ID_EAN13_P5,46,4D,00150B,46,,,\n" +
            "SCAN_SDT_EAN13_S,119,CI_LABEL_ID_EAN13_P8,46,23,,46,,,\n" +
            "SCAN_SDT_EAN8,103,CI_LABEL_ID_EAN8,4646,41,0C,4646,,,\n" +
            "SCAN_SDT_EAN8_S,118,CI_LABEL_ID_EAN8_COMPOSITE,4646,2447,00220B,4646,,,\n" +
            "SCAN_SDT_EAN8_S,118,CI_LABEL_ID_EAN8_P2,4646,4A,00170B,4646,,,\n" +
            "SCAN_SDT_EAN8_S,118,CI_LABEL_ID_EAN8_P5,4646,4B,001D0B,4646,,,\n" +
            "SCAN_SDT_EAN8_S,118,CI_LABEL_ID_EAN8_P8,4646,2A,,4646,,,\n" +
            "SCAN_SDT_ITF,106,CI_LABEL_ID_FOLLET_2OF5,4F,4F,,,,,\n" +
            "SCAN_SDT_GS1DATAMATRIX,208,CI_LABEL_ID_GS1_DATAMATRIX,4467,2477,00360B,4467,,,\n" +
            "SCAN_SDT_GS1QRCODE,209,CI_LABEL_ID_GS1_QR_CODE,5147,2471,00370B,5147,,,\n" +
            "SCAN_SDT_OTHER,501,CI_LABEL_ID_GTIN,47,2441,,47,,,\n" +
            "SCAN_SDT_OTHER,501,CI_LABEL_ID_GTIN2,4732,2442,,4732,,,\n" +
            "SCAN_SDT_OTHER,501,CI_LABEL_ID_GTIN5,4735,2443,,4735,,,\n" +
            "SCAN_SDT_OTHER,501,CI_LABEL_ID_GTIN8,4738,2444,,4738,,,\n" +
            "SCAN_SDT_ITF,106,CI_LABEL_ID_I2OF5,69,4E,000D0B,4232,,,\n" +
            "SCAN_SDT_ITF,106,CI_LABEL_ID_I2OF5_CIP_HR,65,65,,,,,\n" +
            "SCAN_SDT_TF,105,CI_LABEL_ID_IATA_INDUSTRIAL_2OF5,4941,26,,,,,\n" +
            "SCAN_SDT_TF,105,CI_LABEL_ID_INDUSTRIAL_2OF5,57,57,,,,,\n" +
            "SCAN_SDT_EAN13,104,CI_LABEL_ID_ISBN,49,40,,49,,,\n" +
            "SCAN_SDT_ISBT128,141,CI_LABEL_ID_ISBT128_CONCAT,66,66,,,,,\n" +
            "SCAN_SDT_TFMAT,136,CI_LABEL_ID_MATRIX_2OF5,67,67,,,,,\n" +
            "SCAN_SDT_MAXICODE,202,CI_LABEL_ID_MAXICODE,4D43,78,002F0B,,,,\n" +
            "SCAN_SDT_PDF417,201,CI_LABEL_ID_PDF417,50,72,002E0B,50,,,\n" +
            "SCAN_SDT_UPDF417,207,CI_LABEL_ID_MICRO_PDF,6D50,38,00380B,6D50,,,\n" +
            "SCAN_SDT_QRCODE,204,CI_LABEL_ID_QR_CODE,5152,79,00330B,5152,,,\n" +
            "SCAN_SDT_UQRCODE,205,CI_LABEL_ID_MICROQR,2451,2451,00330B,2451,,,\n" +
            "SCAN_SDT_MSI,143,CI_LABEL_ID_MSI,40,5A,,40,,,\n" +
            "SCAN_SDT_Codabar,107,CI_LABEL_ID_NW7_CODABAR,244E,244E,,,,,\n" +
            "SCAN_SDT_PLESSEY,144,CI_LABEL_ID_PLESSEY,61,61,,,,,\n" +
            "SCAN_SDT_AusPost,301,CI_LABEL_ID_POSTAL_AUSTRALIAN,244B,244B,,,,,\n" +
            "SCAN_SDT_UsIntelligent,310,CI_LABEL_ID_POSTAL_IMB,2456,2456,,,,,\n" +
            "SCAN_SDT_JapanPost,306,CI_LABEL_ID_POSTAL_JAPANESE,2452,2452,,,,,\n" +
            "SCAN_SDT_DutchKix,304,CI_LABEL_ID_POSTAL_KIX,2455,2455,,,,,\n" +
            "SCAN_SDT_UsPlanet,311,CI_LABEL_ID_POSTAL_PLANET,2457,2457,,,,,\n" +
            "SCAN_SDT_OTHER,501,CI_LABEL_ID_POSTAL_PORTUGAL,2450,2450,,,,,\n" +
            "SCAN_SDT_UkPost,309,CI_LABEL_ID_POSTAL_ROYAL_MAIL,244D,244D,,,,,\n" +
            "SCAN_SDT_SwedenPost,308,CI_LABEL_ID_POSTAL_SWEDISH,2458,2458,,,,,\n" +
            "SCAN_SDT_PostNet,312,CI_LABEL_ID_POSTNET,244C,244C,,,,,\n" +
            "SCAN_SDT_TRIOPTIC39,140,CI_LABEL_ID_TRIOPTIC,2454,2454,,,,,\n" +
            "SCAN_SDT_UPCA,101,CI_LABEL_ID_UPCA,41,43,0D,41,,,\n" +
            "SCAN_SDT_UPCA_S,111,CI_LABEL_ID_UPCA_COMPOSITE,41,2448,00200B,41,,,\n" +
            "SCAN_SDT_UPCA_S,111,CI_LABEL_ID_UPCA_P2,41,46,00160B,41,,,\n" +
            "SCAN_SDT_UPCA_S,111,CI_LABEL_ID_UPCA_P5,41,47,00110B,41,,,\n" +
            "SCAN_SDT_UPCA_S,111,CI_LABEL_ID_UPCA_P8,41,51,,41,,,\n" +
            "SCAN_SDT_UPCE,102,CI_LABEL_ID_UPCE,45,44,0A,45,,,\n" +
            "SCAN_SDT_UPCE_S,112,CI_LABEL_ID_UPCE_COMPOSITE,45,244A,00210B,45,,,\n" +
            "SCAN_SDT_UPCE_S,112,CI_LABEL_ID_UPCE_P2,45,48,00120B,45,,,\n" +
            "SCAN_SDT_UPCE_S,112,CI_LABEL_ID_UPCE_P5,45,49,00140B,45,,,\n" +
            "SCAN_SDT_UPCE_S,112,CI_LABEL_ID_UPCE_P8,45,45,,45,,,\n" +
            "SCAN_SDT_Code32,138,CI_LABEL_ID_CODE32,,58,,,,,\n" +
            "SCAN_SDT_OCRA,121,CI_LABEL_ID_OCR_A,246F,246F,00300B,,,,\n" +
            "SCAN_SDT_OCRB,122,CI_LABEL_ID_OCR_B,2470,2470,00310B,,,,\n" +
            "SCAN_SDT_UNKNOWN,0,CI_LABEL_ID_UNKNOWN,,,00FF0B,,,,";

    @Test
    public void load() throws APosException {
        BufferedReader reader = new BufferedReader(new StringReader(LABELS_CSV));
        List<LabelIds> result = LabelCSVHelper.load(reader);
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.get(0).getUposId()).isEqualTo(107);
        assertThat(result.get(0).getUposName()).isEqualTo("SCAN_SDT_Codabar");
        assertThat(result.get(0).getTagName()).isEqualTo("CI_LABEL_ID_ABC_CODABAR");
        assertThat(result.get(0).getUsCode()).isEqualTo("53");
        assertThat(result.get(0).getEuCode()).isEqualTo("53");
        assertThat(result.get(0).getIbmUsbOem()).isEqualTo("");
        assertThat(result.get(0).getScrs232()).isEqualTo("");
        assertThat(result.get(0).getOutputPrefix()).isEqualTo("");
        assertThat(result.get(0).getOutputSuffix()).isEqualTo("");

        assertThat(result.get(2).getUposId()).isEqualTo(206);
        assertThat(result.get(2).getUposName()).isEqualTo("SCAN_SDT_AZTEC");
        assertThat(result.get(2).getTagName()).isEqualTo("CI_LABEL_ID_AZTEC");
        assertThat(result.get(2).getUsCode()).isEqualTo("417A");
        assertThat(result.get(2).getEuCode()).isEqualTo("21");
        assertThat(result.get(2).getIbmUsbOem()).isEqualTo("00340B");
        assertThat(result.get(2).getScrs232()).isEqualTo("417A");
        assertThat(result.get(2).getOutputPrefix()).isEqualTo("");
        assertThat(result.get(2).getOutputSuffix()).isEqualTo("");

        int lastIndex = result.size() - 1;
        assertThat(result.get(lastIndex).getUposId()).isEqualTo(0);
        assertThat(result.get(lastIndex).getUposName()).isEqualTo("SCAN_SDT_UNKNOWN");
        assertThat(result.get(lastIndex).getTagName()).isEqualTo("CI_LABEL_ID_UNKNOWN");
        assertThat(result.get(lastIndex).getUsCode()).isEqualTo("");
        assertThat(result.get(lastIndex).getEuCode()).isEqualTo("");
        assertThat(result.get(lastIndex).getIbmUsbOem()).isEqualTo("00FF0B");
        assertThat(result.get(lastIndex).getScrs232()).isEqualTo("");
        assertThat(result.get(lastIndex).getOutputPrefix()).isEqualTo("");
        assertThat(result.get(lastIndex).getOutputSuffix()).isEqualTo("");
    }

    @Test(expected = APosException.class)
    public void loadMalformedString() throws APosException {
        BufferedReader reader = new BufferedReader(new StringReader("a,b,c,d"));
        LabelCSVHelper.load(reader);
    }

    @Test(expected = APosException.class)
    public void loadNotANumberUposId() throws APosException {
        BufferedReader reader = new BufferedReader(new StringReader("a,b,c,d,e,f,g,h,i"));
        LabelCSVHelper.load(reader);
    }
}